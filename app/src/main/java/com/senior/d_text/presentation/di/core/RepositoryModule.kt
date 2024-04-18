package com.senior.d_text.presentation.di.core

import android.content.Context
import com.senior.d_text.data.repository.authentication.AuthenticationRepositoryImpl
import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import com.senior.d_text.data.repository.history.HistoryRepositoryImpl
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import com.senior.d_text.data.repository.message.MessageRepositoryImpl
import com.senior.d_text.data.repository.message.datasource.MessageLocalDatasource
import com.senior.d_text.data.repository.notificationReceiver.NotificationReceiverRepositoryImpl
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource
import com.senior.d_text.data.repository.sms.SMSRepositoryImpl
import com.senior.d_text.domain.repository.AuthenticationRepository
import com.senior.d_text.domain.repository.HistoryRepository
import com.senior.d_text.domain.repository.MessageRepository
import com.senior.d_text.domain.repository.NotificationReceiverRepository
import com.senior.d_text.domain.repository.NotificationRepository
import com.senior.d_text.domain.repository.SMSRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideSMSRepository(): SMSRepository {
        return SMSRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideHistoryRepository(
        historyLocalDatasource: HistoryLocalDatasource
    ): HistoryRepository {
        return HistoryRepositoryImpl(
            historyLocalDatasource
        )
    }

    @Singleton
    @Provides
    fun provideMessageRepository(
        messageLocalDatasource: MessageLocalDatasource
    ): MessageRepository {
        return MessageRepositoryImpl(
            messageLocalDatasource
        )
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        authenticationRemoteDataSource: AuthenticationRemoteDataSource
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            authenticationRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideNotificationReceiverRepository(
        notificationReceiverDatasource: NotificationReceiverDatasource
    ): NotificationReceiverRepository {
        return NotificationReceiverRepositoryImpl(
            notificationReceiverDatasource
        )
    }

}