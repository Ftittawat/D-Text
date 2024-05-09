package com.senior.d_text.presentation.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.domain.usecase.AnalysisUrlMessageServiceUseCase
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.DeleteAllNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteAllNotificationUseCase
import com.senior.d_text.domain.usecase.DeleteHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteNotificationUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.domain.usecase.GetNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.GetNotificationUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.RefreshUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.domain.usecase.SignInUseCase
import com.senior.d_text.domain.usecase.SignOutUseCase
import com.senior.d_text.domain.usecase.SignUpUseCase
import com.senior.d_text.presentation.authentication.AuthenticationViewModel
import com.senior.d_text.presentation.authentication.signin.SignInViewModel
import com.senior.d_text.presentation.authentication.signup.SignUpViewModel
import com.senior.d_text.presentation.autoscan.AutoScanViewModel
import com.senior.d_text.presentation.home.HomeViewModel
import com.senior.d_text.presentation.setting.SettingViewModel
import com.senior.d_text.presentation.setting.messagehistory.MessageHistoryViewModel
import com.senior.d_text.presentation.setting.notificationhistory.NotificationHistoryViewModel

class HomeViewModelFactory(
    private val application: Application,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase,
    private val analysisUrlUseCase: AnalysisUrlUseCase,
    private val refreshUseCase: RefreshUseCase
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            application,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase,
            deleteHistoryUseCase,
            analysisUrlUseCase,
            refreshUseCase
        ) as T
    }
}

class AutoScanViewModelFactory(
    private val application: Application,
    private val analysisUrlUseCase: AnalysisUrlUseCase,
    private val getNotificationUseCase: GetNotificationUseCase,
    private val deleteAllNotificationUseCase: DeleteAllNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AutoScanViewModel(
            application,
            analysisUrlUseCase,
            getNotificationUseCase,
            deleteAllNotificationUseCase,
            deleteNotificationUseCase
        ) as T
    }
}

class AuthenticationViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel() as T
    }
}

class SignInViewModelFactory(
    private val application: Application,
    private val signInUseCase: SignInUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignInViewModel(
            application,
            signInUseCase
        ) as T
    }
}

class SignUpViewModelFactory(
    private val application: Application,
    private val signUpUseCase: SignUpUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(
            application,
            signUpUseCase
        ) as T
    }
}

class SettingViewModelFactory(
    private val application: Application,
    private val signOutUseCase: SignOutUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  SettingViewModel(
            application,
            signOutUseCase
        ) as T
    }
}

class MessageHistoryViewModelFactory(
    private val getMessageUseCase: GetMessageUseCase,
    private val deleteAllMessageUseCase: DeleteAllMessageUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MessageHistoryViewModel(
            getMessageUseCase,
            deleteAllMessageUseCase
        ) as T
    }
}

class NotificationHistoryViewModelFactory(
    private val getNotificationHistoryUseCase: GetNotificationHistoryUseCase,
    private val deleteAllNotificationHistoryUseCase: DeleteAllNotificationHistoryUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationHistoryViewModel(
            getNotificationHistoryUseCase,
            deleteAllNotificationHistoryUseCase
        ) as T
    }
}