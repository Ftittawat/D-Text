package com.senior.d_text.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.senior.d_text.R
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.data.repository.sms.SMSRepositoryImpl
import com.senior.d_text.domain.repository.SMSRepository
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import com.senior.d_text.presentation.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MessageService : Service() {

    lateinit var listenForMessagesUseCase: ListenForMessagesUseCase
    private lateinit var smsRepository: SMSRepository
    @Inject
    lateinit var saveMessageUseCase: SaveMessageUseCase

    private var lastMessage: ReceiveSMS? = null
    private var newMessage: ReceiveSMS? = null

    override fun onCreate() {
        super.onCreate()
        (application as Injector).createMessageService().inject(this)
        smsRepository = SMSRepositoryImpl(applicationContext)
        listenForMessagesUseCase = ListenForMessagesUseCase(smsRepository)

        Log.d("logMessages", "onCreate: MessageService")
//        val channelId = "MessageForegroundServiceChannel"
        val groupKey = "AutoDetect"
//        val channel = NotificationChannel(
//            channelId,
//            "Message Foreground Service Channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//        val notificationManager: NotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
        val notificationBuilder = NotificationCompat.Builder(this, "AutoDetectionService")
            .setContentTitle(getString(R.string.message_auto_scan_title))
            .setContentText(getString(R.string.message_auto_scan_des))
            .setSmallIcon(R.drawable.logo_blue)
            .setGroup(groupKey)
        val notification = notificationBuilder.build()
        startForeground(1, notification)
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d("logMessages", "onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //stopSelf()
        Log.d("logMessages", "onStartCommand")
        startListeningForMessages()
        return START_STICKY
    }

    private fun startListeningForMessages() {
        listenForMessagesUseCase { message ->
            if (lastMessage == null || message.messageBody != lastMessage!!.messageBody) {
                lastMessage = message
                Log.d("logMessages", "messagesSender: ${lastMessage!!.sender}")
                Log.d("logMessages", "messagesBody: ${lastMessage!!.messageBody}")
                saveMessageHistory(lastMessage!!)
            }
            // lastMessage.sender = message.sender
            // lastMessage.messageBody = message.messageBody
            // val currentMessages = messages.toMutableList()
            // currentMessages.add(message)
            // Log.d("sms", "startListeningForMessages: $currentMessages")
            // Log.d("sms", "startListeningForMessages: ${_messages.value}")

        }
    }

    fun loadNotification(tag: String): Boolean {
        val sharePref = application.getSharedPreferences("Setting_Notification", AppCompatActivity.MODE_PRIVATE)
        return sharePref.getBoolean(tag, false)
    }

    private fun saveMessageHistory(message: ReceiveSMS) {
        val saveMessage = Message(0, message.sender, message.messageBody, message.dateTime)
        CoroutineScope(Dispatchers.IO).launch {
            saveMessageUseCase.execute(saveMessage)
        }
    }

    private fun testSaveMessageHistory() {
        val saveMessage = Message(0, "+66910363409", "message.messageBody message.messageBody message.messageBody message.messageBody message.messageBody","17/04/2024 12:30:21")
        CoroutineScope(Dispatchers.IO).launch {
            saveMessageUseCase.execute(saveMessage)
        }
    }

    companion object {
        const val NOTIFICATION = "notification"
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
    }

}