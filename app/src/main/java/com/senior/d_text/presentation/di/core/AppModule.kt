package com.senior.d_text.presentation.di.core

import android.content.Context
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    HomeSubComponent::class,
    MessageHistorySubComponent::class,
    MessageServiceSubComponent::class,
    NotificationServiceSubComponent::class
])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }

}