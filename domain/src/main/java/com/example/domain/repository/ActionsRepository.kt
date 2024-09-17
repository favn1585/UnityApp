package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface ActionsRepository {
    fun observeActions(): Flow<List<String>>
}