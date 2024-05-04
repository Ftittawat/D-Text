package com.senior.d_text.presentation.di.setting

import android.app.Application
import com.senior.d_text.domain.usecase.SignOutUseCase
import com.senior.d_text.presentation.core.SettingViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SettingModule {
    @SettingScope
    @Provides
    fun provideSettingViewModelFactory(
        application: Application,
        signOutUseCase: SignOutUseCase
    ): SettingViewModelFactory {
        return SettingViewModelFactory(
            application,
            signOutUseCase
        )
    }
}