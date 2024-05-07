package com.senior.d_text.presentation.di.core

import android.app.Application
import android.content.Context
import com.senior.d_text.presentation.di.authentication.AuthenticationSubcomponent
import com.senior.d_text.presentation.di.authentication.signin.SignInSubComponent
import com.senior.d_text.presentation.di.authentication.signup.SignUpSubComponent
import com.senior.d_text.presentation.di.autoscan.AutoScanSubComponent
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import com.senior.d_text.presentation.di.setting.SettingSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    HomeSubComponent::class,
    MessageHistorySubComponent::class,
    MessageServiceSubComponent::class,
    NotificationServiceSubComponent::class,
    AuthenticationSubcomponent::class,
    SignInSubComponent::class,
    SignUpSubComponent::class,
    SettingSubComponent::class,
    AutoScanSubComponent::class
])
class AppModule(private val context: Context, private val application: Application) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

}