package com.senior.d_text.presentation.di.core

import android.content.Context
import com.senior.d_text.data.repository.history.HistoryRepositoryImpl
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import com.senior.d_text.data.repository.sms.SMSRepositoryImpl
import com.senior.d_text.domain.repository.HistoryRepository
import com.senior.d_text.domain.repository.SMSRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideSMSRepository(): SMSRepository {
        return SMSRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(
        historyLocalDatasource: HistoryLocalDatasource
    ): HistoryRepository {
        return HistoryRepositoryImpl(
            historyLocalDatasource
        )
    }
}