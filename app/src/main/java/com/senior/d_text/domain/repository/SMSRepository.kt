package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.message.SMSMessage

interface SMSRepository {
    fun listenForMessages(listener: (SMSMessage) -> Unit)
}