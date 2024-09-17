package com.example.repository.repository

import com.example.domain.repository.ActionsRepository
import com.example.local.repository.LocalActionsRepository
import com.example.network.repository.NetworkActionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val networkActionsRepository: NetworkActionsRepository,
    private val localActionsRepository: LocalActionsRepository
) : ActionsRepository {
    override suspend fun getActions() = withContext(Dispatchers.IO) {
        networkActionsRepository.getActions()
    }

    override suspend fun performAction(name: String) {
        localActionsRepository.performAction(name)
    }

    override suspend fun lastPerformedTime(name: String): Long {
        return localActionsRepository.getPerformedTime(name) ?: 0
    }
}