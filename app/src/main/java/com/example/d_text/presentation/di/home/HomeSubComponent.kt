package com.example.d_text.presentation.di.home

import com.example.d_text.presentation.home.HomeActivity
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeActivity: HomeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create():HomeSubComponent
    }
}