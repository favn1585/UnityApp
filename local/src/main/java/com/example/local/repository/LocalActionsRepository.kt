package com.example.local.repository

import com.example.local.ActionsDatabase
import com.example.local.model.PerformedActionEntity
import javax.inject.Inject

class LocalActionsRepository @Inject constructor(
    private val database: ActionsDatabase
) {
    suspend fun performAction(name: String) {
        database.dao.insert(PerformedActionEntity(name, System.currentTimeMillis()))
    }

    suspend fun getPerformedTime(name: String): Long? {
        return database.dao.getPerformedAction(name)?.performedAt
    }
}