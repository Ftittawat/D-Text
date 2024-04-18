package com.senior.d_text.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.di.core.AppComponent
import com.senior.d_text.presentation.di.core.AppModule
import com.senior.d_text.presentation.di.core.DaggerAppComponent
import com.senior.d_text.presentation.di.core.RepositoryModule
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .repositoryModule(RepositoryModule(applicationContext))
            .build()
        initNotificationChannel(applicationContext)
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

    fun initNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = "test_notification"
            val name = "New Notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}