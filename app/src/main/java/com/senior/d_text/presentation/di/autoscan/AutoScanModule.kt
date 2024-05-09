package com.senior.d_text.presentation.di.autoscan

import android.app.Application
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllNotificationUseCase
import com.senior.d_text.domain.usecase.DeleteNotificationUseCase
import com.senior.d_text.domain.usecase.GetNotificationUseCase
import com.senior.d_text.presentation.core.AutoScanViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AutoScanModule {
    @AutoScanScope
    @Provides
    fun provideAutoScanViewModelFactory(
        application: Application,
        analysisUrlUseCase: AnalysisUrlUseCase,
        getNotificationUseCase: GetNotificationUseCase,
        deleteAllNotificationUseCase: DeleteAllNotificationUseCase,
        deleteNotificationUseCase: DeleteNotificationUseCase
    ): AutoScanViewModelFactory {
        return AutoScanViewModelFactory(
            application,
            analysisUrlUseCase,
            getNotificationUseCase,
            deleteAllNotificationUseCase,
            deleteNotificationUseCase
        )
    }
}