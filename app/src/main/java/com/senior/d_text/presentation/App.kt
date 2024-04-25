package com.senior.d_text.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.di.core.AppComponent
import com.senior.d_text.presentation.di.core.AppModule
import com.senior.d_text.presentation.di.core.DaggerAppComponent
import com.senior.d_text.presentation.di.core.RepositoryModule
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import com.senior.d_text.presentation.util.NotificationFactory

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .repositoryModule(RepositoryModule(applicationContext))
            .build()
//        notificationFactory.createNotificationChannel("MessageForegroundServiceChannel", "Message Foreground Service Channel")
//        notificationFactory.createNotificationChannel("MessageForegroundServiceChannel", "Notification Foreground Service Channel")
        NotificationFactory(applicationContext)
            .createNotificationChannel("AutoDetectionService", "Auto Detection Service")
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }

    override fun createMessageHistorySubComponent(): MessageHistorySubComponent {
        return appComponent.messageHistorySubComponent().create()
    }

    override fun createMessageService(): MessageServiceSubComponent {
        return appComponent.messageServiceSubComponent().create()
    }

    override fun createNotificationService(): NotificationServiceSubComponent {
        return appComponent.notificationServiceSubComponent().create()
    }

    fun initNotificationChannel() {
        val channelId = "MessageForegroundServiceChannel"
        val channel = NotificationChannel(
            channelId,
            "Message Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}