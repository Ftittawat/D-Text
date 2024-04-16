package com.senior.d_text.presentation.di.core

import com.senior.d_text.presentation.di.home.HomeSubComponent
import com.senior.d_text.presentation.home.HomeActivity
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
}