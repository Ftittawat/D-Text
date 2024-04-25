package com.senior.d_text.presentation.di.core

import com.senior.d_text.data.db.HistoryDao
import com.senior.d_text.data.db.MessageDao
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import com.senior.d_text.data.repository.history.datasourceImpl.HistoryLocalDatasourceImpl
import com.senior.d_text.data.repository.message.datasource.MessageLocalDatasource
import com.senior.d_text.data.repository.message.datasourceImpl.MessageLocalDatasourceImpl
import com.senior.d_text.data.repository.notificationReceiver.datasource.NotificationReceiverDatasource
import com.senior.d_text.data.repository.notificationReceiver.datasourceImpl.NotificationReceiverDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Singleton
    @Provides
    fun provideHistoryDataSource(historyDao: HistoryDao): HistoryLocalDatasource {
        return HistoryLocalDatasourceImpl(historyDao)
    }

    @Singleton
    @Provides
    fun provideMessageDataSource(messageDao: MessageDao): MessageLocalDatasource {
        return MessageLocalDatasourceImpl(messageDao)
    }

    @Singleton
    @Provides
    fun provideNotificationReceiverDatasource(): NotificationReceiverDatasource {
        return NotificationReceiverDatasourceImpl()
    }
}