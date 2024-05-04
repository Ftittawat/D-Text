package com.senior.d_text.data.repository.message.datasource

import com.senior.d_text.data.model.message.Message
import kotlinx.coroutines.flow.Flow

interface MessageLocalDatasource {
    suspend fun saveMessage(message: Message)
    fun getMessageFromDB(): Flow<List<Message>>
    suspend fun clearAll()
}