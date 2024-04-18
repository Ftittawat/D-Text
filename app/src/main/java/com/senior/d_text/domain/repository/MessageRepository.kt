package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.message.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessage(): Flow<List<Message>>
    suspend fun saveMessage(message: Message)
    suspend fun deleteAllMessage()
}