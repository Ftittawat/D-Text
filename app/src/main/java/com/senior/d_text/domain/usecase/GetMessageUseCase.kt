package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.message.Message
import com.senior.d_text.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow

class GetMessageUseCase(private val messageRepository: MessageRepository) {
    fun execute(): Flow<List<Message>>? = messageRepository.getMessage()
}