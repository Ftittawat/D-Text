package com.senior.d_text.domain.usecase

import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.domain.repository.SMSRepository

class ListenForMessagesUseCase(private val smsRepository: SMSRepository) {
    operator fun invoke(listener: (ReceiveSMS) -> Unit) {
        smsRepository.listenForMessages(listener)
    }
}