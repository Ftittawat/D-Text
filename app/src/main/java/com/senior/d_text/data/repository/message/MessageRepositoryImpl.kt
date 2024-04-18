package com.senior.d_text.data.repository.message

import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.repository.message.datasource.MessageLocalDatasource
import com.senior.d_text.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(
    private val messageLocalDatasource: MessageLocalDatasource
    ): MessageRepository {

    override fun getMessage(): Flow<List<Message>> {
        return messageLocalDatasource.getMessageFromDB()
    }

    override suspend fun saveMessage(message: Message) {
        messageLocalDatasource.saveMessage(message)
    }

    override suspend fun deleteAllMessage() {
        messageLocalDatasource.clearAll()
    }
}