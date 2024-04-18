package com.senior.d_text.domain.usecase

import com.senior.d_text.domain.repository.MessageRepository

class DeleteAllMessageUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute() = messageRepository.deleteAllMessage()
}