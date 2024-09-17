package com.example.network.repository

import com.example.domain.repository.ActionsRepository
import com.example.network.api.ActionsApi
import com.example.network.model.toActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActionsRepositoryImpl @Inject constructor(
    private val actionsApi: ActionsApi
) : ActionsRepository {
    override suspend fun getActions() = withContext(Dispatchers.IO) {
        actionsApi.getActions().map {
            it.toActions()
        }
    }
}