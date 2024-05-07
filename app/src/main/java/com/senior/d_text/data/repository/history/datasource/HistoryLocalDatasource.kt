package com.senior.d_text.data.repository.history.datasource

import androidx.lifecycle.MutableLiveData
import com.senior.d_text.data.model.history.History
import kotlinx.coroutines.flow.Flow

interface HistoryLocalDatasource {
    suspend fun saveHistory(history: History)
    fun getHistoryFromDB(): Flow<List<History>>
    suspend fun clearAll()
    suspend fun deleteFirstHistory()
    suspend fun deleteHistory(id: Int)
}