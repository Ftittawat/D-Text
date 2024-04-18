package com.senior.d_text.presentation.di.core

import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.di.messagehistory.MessageHistorySubComponent
import com.senior.d_text.presentation.di.messageService.MessageServiceSubComponent
import com.senior.d_text.presentation.di.notificationService.NotificationServiceSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    DataBaseModule::class,
    LocalDataModule::class,
    RemoteDataModule::class
])
interface AppComponent {
    fun homeSubComponent(): HomeSubComponent.Factory
    fun messageHistorySubComponent(): MessageHistorySubComponent.Factory
    fun messageServiceSubComponent(): MessageServiceSubComponent.Factory
    fun notificationServiceSubComponent(): NotificationServiceSubComponent.Factory
}