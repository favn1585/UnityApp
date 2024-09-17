package com.example.local.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "performed_actions")
data class PerformedActionEntity(
    @PrimaryKey val action: String,
    val performedAt: Long,
)
