package com.senior.d_text.presentation.setting.notificationhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.senior.d_text.domain.usecase.DeleteAllNotificationHistoryUseCase
import com.senior.d_text.domain.usecase.GetNotificationHistoryUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotificationHistoryViewModel(
    private val getNotificationHistoryUseCase: GetNotificationHistoryUseCase,
    private val deleteAllNotificationHistoryUseCase: DeleteAllNotificationHistoryUseCase
): ViewModel() {

    fun getNotificationHistory() = liveData {
        getNotificationHistoryUseCase.execute()?.collect() {
            emit(it)
        }
    }

    fun deleteAllNotificationHistory() = viewModelScope.launch {
        deleteAllNotificationHistoryUseCase.execute()
    }

}