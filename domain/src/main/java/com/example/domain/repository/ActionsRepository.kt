package com.example.domain.repository

import com.example.domain.entity.Action

interface ActionsRepository {
    suspend fun getActions(): List<Action>
}