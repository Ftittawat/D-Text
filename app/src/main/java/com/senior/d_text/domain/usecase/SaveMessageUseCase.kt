package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.message.Message
import com.senior.d_text.domain.repository.MessageRepository

class SaveMessageUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(message: Message) = messageRepository.saveMessage(message)
}