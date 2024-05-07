package com.senior.d_text.presentation.di.core

import com.senior.d_text.presentation.di.authentication.AuthenticationSubcomponent
import com.senior.d_text.presentation.di.authentication.signin.SignInSubComponent
import com.senior.d_text.presentation.di.authentication.signup.SignUpSubComponent
import com.senior.d_text.presentation.di.autoscan.AutoScanSubComponent
import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import com.senior.d_text.presentation.di.notificationhistory.NotificationHistorySubComponent
import com.senior.d_text.presentation.di.setting.SettingSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    DataBaseModule::class,
    LocalDataModule::class,
    RemoteDataModule::class,
    NetModule::class
])
interface AppComponent {
    fun homeSubComponent(): HomeSubComponent.Factory
    fun messageHistorySubComponent(): MessageHistorySubComponent.Factory
    fun notificationHistorySubComponent(): NotificationHistorySubComponent.Factory
    fun messageServiceSubComponent(): MessageServiceSubComponent.Factory
    fun notificationServiceSubComponent(): NotificationServiceSubComponent.Factory
    fun authenticationSubComponent(): AuthenticationSubcomponent.Factory
    fun signInSubComponent(): SignInSubComponent.Factory
    fun signUpSubComponent(): SignUpSubComponent.Factory
    fun settingSubComponent(): SettingSubComponent.Factory
    fun autoScanSubComponent(): AutoScanSubComponent.Factory
}