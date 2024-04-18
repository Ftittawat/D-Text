package com.senior.d_text.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import com.senior.d_text.presentation.authentication.AuthenticationViewModel
import com.senior.d_text.presentation.home.HomeViewModel
import com.senior.d_text.presentation.setting.messagehistory.MessageHistoryViewModel

class HomeViewModelFactory(
    private val listenForMessagesUseCase: ListenForMessagesUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            listenForMessagesUseCase,
            getHistoryUseCase,
            saveHistoryUseCase,
            deleteAllHistoryUseCase
        ) as T
    }
}

class AuthenticationViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel() as T
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