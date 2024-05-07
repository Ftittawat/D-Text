package com.senior.d_text.data.repository.notificationReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.senior.d_text.data.model.notification.ReceiveNotification
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

class NotificationReceiver: BroadcastReceiver() {

    private var listener: ((ReceiveNotification) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("NotiResult", "onReceive: ")
        if (intent?.action == "com.d-text.ACTION_NOTIFICATION_INTENT") {
            val packageName = intent.getStringExtra("packageName")
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("text")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val currentTime = LocalDateTime.now().format(formatter)
            if (hasUrl(text ?: "")) {
                val url = extractUrl(text!!)
                Log.d("NotiResult", "onReceive: $packageName $title $text $url")
                listener?.invoke(ReceiveNotification(0,packageName?:"", title?:"", text?:"", url?:"", currentTime))
            }
        }
    }

    fun setListener(listener: (ReceiveNotification) -> Unit) {
        this.listener = listener
    }

    private fun extractUrl(message: String): String? {
        val pattern = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
        val matchResult = pattern.find(message)
        return matchResult?.value
    }

    private fun hasUrl(text: String): Boolean {
        val urlRegex = Regex("((http|https)://)?(www\\.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
        return urlRegex.containsMatchIn(text)
    }

}