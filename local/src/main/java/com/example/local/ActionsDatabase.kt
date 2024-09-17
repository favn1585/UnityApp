package com.example.local

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.dao.PerformedActionDao
import com.example.local.model.PerformedActionEntity

@Keep
@Database(
    entities = [PerformedActionEntity::class], version = 1
)

abstract class ActionsDatabase : RoomDatabase() {
    abstract val dao: PerformedActionDao
}
