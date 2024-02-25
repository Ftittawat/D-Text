package com.example.d_text.presentation.di.core

import com.example.d_text.presentation.di.home.HomeSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent {
    fun homeSubComponent():HomeSubComponent.Factory
}