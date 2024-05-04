package com.senior.d_text.data.repository.history

import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import com.senior.d_text.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(
    private val historyLocalDatasource: HistoryLocalDatasource
): HistoryRepository {

    override fun getHistory(): Flow<List<History>>? {
        return historyLocalDatasource.getHistoryFromDB()
    }

    override suspend fun saveHistory(history: History) {
        historyLocalDatasource.saveHistory(history)
    }

    override suspend fun deleteAllHistory() {
        historyLocalDatasource.clearAll()
    }

}