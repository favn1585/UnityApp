package com.example.unityapp.feature.actions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Action
import com.example.domain.entity.ActionType
import com.example.unityapp.feature.actions.interactors.GetActionsUseCase
import com.example.unityapp.feature.actions.interactors.GetCurrentDayOfWeekUseCase
import com.example.unityapp.feature.actions.interactors.GetLastPerformedTimeUseCase
import com.example.unityapp.feature.actions.interactors.PerformActionUseCase
import com.example.unityapp.feature.actions.interactors.ShowContactPickerUseCase
import com.example.unityapp.feature.actions.interactors.ShowToastUseCase
import com.example.unityapp.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val showToastUseCase: ShowToastUseCase,
    private val getActionsUseCase: GetActionsUseCase,
    private val getCurrentDayOfWeekUseCase: GetCurrentDayOfWeekUseCase,
    private val getLastPerformedTimeUseCase: GetLastPerformedTimeUseCase,
    private val performActionUseCase: PerformActionUseCase,
    private val showContactPickerUseCase: ShowContactPickerUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _viewState = MutableStateFlow(ActionsViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<ActionsEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    var actions = listOf<Action>()

    init {
        fetchActions()
    }

    fun onUiIntent(intent: ActionsUiIntent) {
        when (intent) {
            is ActionsUiIntent.OnButtonClick -> onButtonClick()
        }
    }

    private fun fetchActions() {
        viewModelScope.launch {
            getActionsUseCase().run {
                if (isSuccess) {
                    getOrNull()?.let {
                        actions = it
                    }
                    _viewState.update { it.copy(isLoading = false) }
                } else {
                    _viewState.update { it.copy(isError = true) }
                }
            }
        }
    }

    private fun onButtonClick() {
        val currentDayOfWeek = getCurrentDayOfWeekUseCase()

        viewModelScope.launch {
            val actions = actions.filter { it.enabled }
                .sortedBy { it.priority }
                .filter { it.validDays.contains(currentDayOfWeek) }
                .filter { System.currentTimeMillis() - getLastPerformedTimeUseCase(it.type.name) > it.coolDown }
            actions.firstOrNull()?.let {
                handleAction(it.type)
            }
        }

    }

    private fun handleAction(actionType: ActionType) {
        when (actionType) {
            ActionType.TOAST -> if (networkMonitor.isConnected.value) {
                showToastUseCase()
            }

            ActionType.ANIMATION -> viewModelScope.launch {
                _viewEvent.emit(ActionsEvent.RotateButton)
            }

            ActionType.CALL -> viewModelScope.launch {
                showContactPickerUseCase()
            }

            ActionType.NOTIFICATION -> viewModelScope.launch {
                //TODO show notification with pending intent and create notification channels
            }
        }

        viewModelScope.launch {
            performActionUseCase(actionType.name)
        }
    }
}