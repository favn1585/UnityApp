package com.example.unityapp.feature.actions.interactors

import com.example.domain.repository.ActionsRepository
import javax.inject.Inject

class PerformActionUseCase @Inject constructor(
    private val actionsRepository: ActionsRepository
) {
    suspend operator fun invoke(name: String) {
        actionsRepository.performAction(name)
    }
}