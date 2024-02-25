package com.example.d_text.presentation.di

import com.example.d_text.presentation.di.home.HomeSubComponent

interface Injector {
    fun createHomeSubComponent():HomeSubComponent
}