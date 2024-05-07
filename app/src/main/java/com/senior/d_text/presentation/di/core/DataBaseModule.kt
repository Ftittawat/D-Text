package com.senior.d_text.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.senior.d_text.data.db.DTextDatabase
import com.senior.d_text.data.db.HistoryDao
import com.senior.d_text.data.db.MessageDao
import com.senior.d_text.data.db.NotificationDao
import com.senior.d_text.data.db.NotificationHistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDTextDataBase(context: Context): DTextDatabase {
        return Room.databaseBuilder(context, DTextDatabase::class.java, "dtextdatabase").build()
    }

    @Singleton
    @Provides
    fun provideHistoryDao(dTextDatabase: DTextDatabase): HistoryDao {
        return dTextDatabase.historyDao()
    }

    @Singleton
    @Provides
    fun provideMessageDao(dTextDatabase: DTextDatabase): MessageDao {
        return dTextDatabase.messageDao()
    }

    @Singleton
    @Provides
    fun provideNotificationHistoryDao(dTextDatabase: DTextDatabase): NotificationHistoryDao {
        return dTextDatabase.notificationHistoryDao()
    }

    @Singleton
    @Provides
    fun provideNotificationDao(dTextDatabase: DTextDatabase): NotificationDao {
        return dTextDatabase.notificationDao()
    }
}