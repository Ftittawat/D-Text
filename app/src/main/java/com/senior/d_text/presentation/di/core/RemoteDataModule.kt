package com.senior.d_text.presentation.di.core

import com.senior.d_text.data.api.DTextService
import com.senior.d_text.data.repository.analysis.datasource.AnalysisRemoteDatasource
import com.senior.d_text.data.repository.analysis.datasourceImpl.AnalysisRemoteDatasourceImpl
import com.senior.d_text.data.repository.authentication.datasource.AuthenticationRemoteDataSource
import com.senior.d_text.data.repository.authentication.datasourceImpl.AuthenticationRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideAuthenticationRemoteDataSource(
        dTextService: DTextService
    ): AuthenticationRemoteDataSource {
        return AuthenticationRemoteDataSourceImpl(dTextService)
    }

    @Singleton
    @Provides
    fun provideAnalysisRemoteDataSource(
        dTextService: DTextService
    ): AnalysisRemoteDatasource {
        return AnalysisRemoteDatasourceImpl(dTextService)
    }

}