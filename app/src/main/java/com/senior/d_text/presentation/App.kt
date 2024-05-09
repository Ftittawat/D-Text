package com.senior.d_text.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.senior.d_text.BuildConfig
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.di.authentication.AuthenticationSubcomponent
import com.senior.d_text.presentation.di.authentication.signin.SignInSubComponent
import com.senior.d_text.presentation.di.authentication.signup.SignUpSubComponent
import com.senior.d_text.presentation.di.autoscan.AutoScanSubComponent
import com.senior.d_text.presentation.di.core.AppComponent
import com.senior.d_text.presentation.di.core.AppModule
import com.senior.d_text.presentation.di.core.DaggerAppComponent
import com.senior.d_text.presentation.di.core.NetModule
import com.senior.d_text.presentation.di.core.RemoteDataModule
import com.senior.d_text.presentation.di.core.RepositoryModule
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import com.senior.d_text.presentation.di.notificationhistory.NotificationHistorySubComponent
import com.senior.d_text.presentation.di.setting.SettingSubComponent
import com.senior.d_text.presentation.util.NotificationFactory

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext, this))
            .repositoryModule(RepositoryModule(applicationContext))
            .netModule(NetModule(BASE_URL))
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

    override fun createNotificationHistorySubComponent(): NotificationHistorySubComponent {
        return appComponent.notificationHistorySubComponent().create()
    }

    override fun createMessageService(): MessageServiceSubComponent {
        return appComponent.messageServiceSubComponent().create()
    }

    override fun createNotificationService(): NotificationServiceSubComponent {
        return appComponent.notificationServiceSubComponent().create()
    }

    override fun createAuthenticationSubComponent(): AuthenticationSubcomponent {
        return appComponent.authenticationSubComponent().create()
    }

    override fun createSignInSubComponent(): SignInSubComponent {
        return appComponent.signInSubComponent().create()
    }

    override fun createSignUpSubComponent(): SignUpSubComponent {
        return appComponent.signUpSubComponent().create()
    }

    override fun createSettingSubComponent(): SettingSubComponent {
        return appComponent.settingSubComponent().create()
    }

    override fun createAutoScanSubComponent(): AutoScanSubComponent {
        return appComponent.autoScanSubComponent().create()
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

    private fun loadUserToken(): UserToken {
        val sharePref = applicationContext.getSharedPreferences("account", Context.MODE_PRIVATE)
        val tokenId = sharePref.getString("token_id", null)
        val accessToken = sharePref.getString("access_token", null)
        val refreshToken = sharePref.getString("refresh_token", null)
        return UserToken(tokenId, accessToken, refreshToken)
    }

    companion object {
        const val BASE_URL_LOCAL = "http://10.0.2.2:8080/"
        const val BASE_URL_ANALYSIS = "http://10.0.2.2:9999/"
        const val BASE_URL = "https://dtext-app-7uaczrrahq-as.a.run.app/"
//        const val BASE_URL = "http://localhost:9999/"
    }
}