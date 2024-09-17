package com.example.network.api

import com.example.network.model.ActionResponse
import retrofit2.http.GET

interface ActionsApi {
    @GET("/androidexam/butto_to_action_config.json")
    suspend fun getActions(): List<ActionResponse>
}