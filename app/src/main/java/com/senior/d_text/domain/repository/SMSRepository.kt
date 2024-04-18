package com.senior.d_text.domain.repository

import com.senior.d_text.data.model.message.ReceiveSMS

interface SMSRepository {
    fun listenForMessages(listener: (ReceiveSMS) -> Unit)
}