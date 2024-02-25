package com.example.d_text.presentation

import android.app.Application
import com.example.d_text.presentation.di.Injector
import com.example.d_text.presentation.di.core.AppComponent
import com.example.d_text.presentation.di.home.HomeSubComponent

class App : Application(), Injector {
    private lateinit var  appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
    }

    override fun createHomeSubComponent(): HomeSubComponent {
        return appComponent.homeSubComponent().create()
    }

}