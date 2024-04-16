package com.senior.d_text.data.repository.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import com.senior.d_text.data.model.message.SMSMessage

//class SMSReceiver(private val listener: (SMSMessage) -> Unit) : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        Log.d("sms", "onReceive: ")
//        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
//            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
//            smsMessages?.forEach { sms ->
//                val sender = sms.originatingAddress ?: ""
//                val messageBody = sms.messageBody ?: ""
//                listener.invoke(SMSMessage(sender, messageBody))
//            }
//        }
//    }
//}

class SMSReceiver : BroadcastReceiver() {

    private var listener: ((SMSMessage) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
//            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
//            smsMessages?.forEach { sms ->
//                Log.d("sms", "messageBody: ${sms.messageBody}")
//                Log.d("sms", "displayMessageBody: ${sms.displayMessageBody}")
//                val sender = sms.originatingAddress ?: ""
//                val messageBody = sms.messageBody ?: ""
//                listener?.invoke(SMSMessage(sender, messageBody))
//            }
            val bundle = intent.extras
            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<*>
                for (pdu in pdus) {
                    val smsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                    val messageBody = smsMessage.messageBody ?: ""
                    val sender = smsMessage.originatingAddress ?: ""

                    val fullMessage = concatenateSmsParts(bundle)
                    // url = extractUrl(messageBody)  ?: ""
                    listener?.invoke(SMSMessage(sender, fullMessage))
                }
            }
        }
    }

    fun setListener(listener: (SMSMessage) -> Unit) {
        this.listener = listener
    }

    private fun concatenateSmsParts(bundle: Bundle): String {
        val pdus = bundle.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(pdus.size)
        val stringBuilder = StringBuilder()
        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
            stringBuilder.append(messages[i]?.messageBody)
        }
        return stringBuilder.toString()
    }

    private fun extractUrl(message: String): String? {
        val pattern = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
        val matchResult = pattern.find(message)
        return matchResult?.value
    }
}
