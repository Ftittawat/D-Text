package com.senior.d_text.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.model.history.HistoryList
import com.senior.d_text.data.model.message.SMSMessage
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val listenForMessagesUseCase: ListenForMessagesUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase
    ): ViewModel() {

    var url = MutableLiveData("")
//    private val _messages = MutableLiveData<SMSMessage>()
//    val messages: MutableLiveData<SMSMessage>()
    val history = MutableLiveData<HistoryList>()
    var messagesSender = MutableLiveData("")
    var messagesBody = MutableLiveData("")
    var messagesUrl = MutableLiveData("")
    private val _messages = MutableLiveData<List<SMSMessage>>()
    val messages: MutableLiveData<List<SMSMessage>> = _messages

    fun validationUrl(value: String): String {
        return if (!value.startsWith("http://") && !value.startsWith("https://")) {
            if (!value.startsWith("www.")) {
                "http://www.$value"
            }
            else {
                "http://$value"
            }
        }
        else {
            value
        }
    }

    fun startListeningForMessages() {
        listenForMessagesUseCase { message ->
            messagesSender.value = message.sender
            messagesBody.value = message.messageBody
            messagesUrl.value = extractUrl(messagesBody.value.toString())
            val currentMessages = _messages.value?.toMutableList() ?: mutableListOf()
            currentMessages.add(message)
            _messages.postValue(currentMessages)
            // Log.d("sms", "startListeningForMessages: $currentMessages")
            // Log.d("sms", "startListeningForMessages: ${_messages.value}")
        }
    }
    private fun extractUrl(message: String): String? {
        val pattern = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
        val matchResult = pattern.find(message)
        return matchResult?.value
    }

    fun saveHistory(history: History) = viewModelScope.launch {
        saveHistoryUseCase.execute(history)
    }

    fun getHistory() = liveData {
        getHistoryUseCase.execute()?.collect {
            emit(it)
        }
    }

    fun deleteAllHistory() = viewModelScope.launch {
        deleteAllHistoryUseCase.execute()
    }

}