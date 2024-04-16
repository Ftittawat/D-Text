package com.senior.d_text.presentation.di.core

import com.senior.d_text.data.db.HistoryDao
import com.senior.d_text.data.repository.history.datasource.HistoryLocalDatasource
import com.senior.d_text.data.repository.history.datasourceImpl.HistoryLocalDatasourceImpl
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
}