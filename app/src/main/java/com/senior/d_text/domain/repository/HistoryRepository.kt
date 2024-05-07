package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.history.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(): Flow<List<History>>?
    suspend fun saveHistory(history: History)
    suspend fun deleteAllHistory()
    suspend fun deleteHistory(id: Int)

}