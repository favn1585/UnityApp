package com.example.network.repository

import com.example.network.api.ActionsApi
import com.example.network.model.toActions
import javax.inject.Inject

class NetworkActionsRepository @Inject constructor(
    private val actionsApi: ActionsApi
) {
    suspend fun getActions() = actionsApi.getActions().map {
        it.toActions()
    }
}