package com.example.repository.repository

import com.example.domain.repository.ActionsRepository
import com.example.network.repository.NetworkActionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val networkActionsRepository: NetworkActionsRepository
) : ActionsRepository {
    override suspend fun getActions() = withContext(Dispatchers.IO) {
        networkActionsRepository.getActions()
    }
}