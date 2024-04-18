package com.senior.d_text.presentation.setting.messagehistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.domain.usecase.DeleteAllMessageUseCase
import com.senior.d_text.domain.usecase.GetMessageUseCase
import com.senior.d_text.domain.usecase.SaveMessageUseCase
import kotlinx.coroutines.launch

class MessageHistoryViewModel(
    private val getMessageUseCase: GetMessageUseCase,
    private val deleteAllMessageUseCase: DeleteAllMessageUseCase
): ViewModel() {

    fun getMessageHistory() = liveData {
        getMessageUseCase.execute()?.collect() {
            emit(it)
        }
    }

    fun deleteAllMessageHistory() = viewModelScope.launch {
        deleteAllMessageUseCase.execute()
    }
}