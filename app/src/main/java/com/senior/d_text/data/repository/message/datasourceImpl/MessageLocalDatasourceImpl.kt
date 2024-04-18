package com.senior.d_text.data.repository.message.datasourceImpl

import com.senior.d_text.data.db.MessageDao
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.repository.message.datasource.MessageLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MessageLocalDatasourceImpl(private val messageDao: MessageDao):
    MessageLocalDatasource {

    override suspend fun saveMessage(message: Message) {
        CoroutineScope(Dispatchers.IO).launch {
            messageDao.saveMessage(message)
        }
    }

    override fun getMessageFromDB(): Flow<List<Message>> {
        return messageDao.getMessage()
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            messageDao.deleteAllMessage()
        }
    }
}