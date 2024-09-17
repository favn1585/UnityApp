package com.example.domain.repository

import com.example.domain.entity.Action

interface ActionsRepository {
    suspend fun getActions(): List<Action>

    suspend fun performAction(name: String)

    suspend fun lastPerformedTime(name: String): Long
}