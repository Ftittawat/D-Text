package com.senior.d_text.data.repository.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log
import com.senior.d_text.data.model.message.ReceiveSMS
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    private var listener: ((ReceiveSMS) -> Unit)? = null

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
                    val timestampMillis = smsMessage.timestampMillis // Get timestamp in milliseconds
                    val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                        .format(Date(timestampMillis))
                    Log.d("logMessage", "onReceive-sender: $sender")
                    Log.d("logMessage", "onReceive-messageBody: $messageBody")

                    val fullMessage = concatenateSmsParts(bundle)
                    Log.d("logMessage", "onReceive-fullMessage: $fullMessage")
                    val url = extractUrl(fullMessage)  ?: ""
                    listener?.invoke(ReceiveSMS(sender, fullMessage, url, dateTime))
                }
            }
        }
    }

    fun setListener(listener: (ReceiveSMS) -> Unit) {
        this.listener = listener
    }

    private fun concatenateSmsParts(bundle: Bundle): String {
        val pdus = bundle.get("pdus") as Array<*>
        val messages = arrayOfNulls<SmsMessage>(pdus.size)
        val stringBuilder = StringBuilder()
        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
            stringBuilder.append(messages[i]?.messageBody)
            Log.d("logMessage", "concatenateSmsParts: $i: ${messages[i]?.messageBody}")
        }
        return stringBuilder.toString()
    }

    private fun extractUrl(message: String): String? {
        val pattern = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
        val matchResult = pattern.find(message)
        return matchResult?.value
    }
}
