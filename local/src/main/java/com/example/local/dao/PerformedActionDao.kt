package com.example.local.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.model.PerformedActionEntity

@Keep
@Dao
interface PerformedActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(action: PerformedActionEntity)

    @Query("SELECT * FROM performed_actions WHERE action = :action LIMIT 1")
    suspend fun getPerformedAction(action: String): PerformedActionEntity?
}