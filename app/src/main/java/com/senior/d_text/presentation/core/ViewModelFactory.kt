package com.senior.d_text.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.presentation.authentication.AuthenticationViewModel
import com.senior.d_text.presentation.home.HomeViewModel
import com.senior.d_text.presentation.setting.SettingViewModel

class HomeViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}

class AuthenticationViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel() as T
    }
}