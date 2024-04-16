package com.senior.d_text.data.repository.history.datasourceImpl

import com.senior.d_text.data.db.HistoryDao
import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HistoryLocalDatasourceImpl(private val historyDao: HistoryDao):
    HistoryLocalDatasource {

    override fun getHistoryFromDB(): Flow<List<History>> {
        return historyDao.getHistory()
    }

    override suspend fun saveHistory(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            historyDao.saveHistory(history)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            historyDao.deteteAllHistory()
        }
    }
}