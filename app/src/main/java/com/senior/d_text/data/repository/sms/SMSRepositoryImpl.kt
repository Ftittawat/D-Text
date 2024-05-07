package com.senior.d_text.data.repository.sms

import android.content.Context
import android.content.IntentFilter
import android.provider.Telephony
import android.util.Log
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.domain.repository.SMSRepository

class SMSRepositoryImpl(private val context: Context): SMSRepository {
    override fun listenForMessages(listener: (ReceiveSMS) -> Unit) {
        Log.d("sms", "listenForMessages: ")
        val smsReceiver = SMSReceiver()
        context.registerReceiver(smsReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        smsReceiver.setListener(listener)
    }
}
