package com.senior.d_text.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senior.d_text.data.model.history.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHistory(history: History)

    @Query("SELECT * FROM history")
    fun getHistory(): Flow<List<History>>

    @Query("DELETE FROM history")
    fun deteteAllHistory()
}