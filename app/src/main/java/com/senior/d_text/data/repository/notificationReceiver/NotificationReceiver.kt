package com.senior.d_text.data.repository.notificationReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.senior.d_text.data.model.notification.ReceiveNotification

class NotificationReceiver: BroadcastReceiver() {

    private var listener: ((ReceiveNotification) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.senior.ACTION_CUSTOM_INTENT") {
            Log.d("NotiResult", "onReceive: ")
            val packageName = intent.getStringExtra("packageName")
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("text")
            listener?.invoke(ReceiveNotification(0,packageName?:"", title?:"", text?:"", "2024-04-28"))
        }
    }

    fun setListener(listener: (ReceiveNotification) -> Unit) {
        this.listener = listener
    }

}