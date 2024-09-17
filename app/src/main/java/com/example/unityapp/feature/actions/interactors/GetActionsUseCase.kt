package com.example.unityapp.feature.actions.interactors

import com.example.domain.repository.ActionsRepository
import javax.inject.Inject

class GetActionsUseCase @Inject constructor(
    private val actionsRepository: ActionsRepository
) {
    suspend operator fun invoke() = try {
        Result.success(actionsRepository.getActions())
    } catch (e: Exception) {
        Result.failure(e)
    }
}