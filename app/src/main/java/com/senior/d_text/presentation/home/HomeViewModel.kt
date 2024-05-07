package com.senior.d_text.presentation.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.model.history.HistoryList
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase
import com.senior.d_text.domain.usecase.DeleteAllHistoryUseCase
import com.senior.d_text.domain.usecase.DeleteHistoryUseCase
import com.senior.d_text.domain.usecase.GetHistoryUseCase
import com.senior.d_text.domain.usecase.ListenForMessagesUseCase
import com.senior.d_text.domain.usecase.RefreshUseCase
import com.senior.d_text.domain.usecase.SaveHistoryUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val application: Application,
    private val listenForMessagesUseCase: ListenForMessagesUseCase,
    private val getHistoryUseCase: GetHistoryUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase,
    private val analysisUrlUseCase: AnalysisUrlUseCase,
    private val refreshUseCase: RefreshUseCase
    ): AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)
    var url = MutableLiveData("")
    var error = MutableLiveData("")
//    private val _messages = MutableLiveData<SMSMessage>()
//    val messages: MutableLiveData<SMSMessage>()
    val history = MutableLiveData<HistoryList>()
    var messagesSender = MutableLiveData("")
    var messagesBody = MutableLiveData("")
    var messagesUrl = MutableLiveData("")
    private val _messages = MutableLiveData<List<ReceiveSMS>>()
    val messages: MutableLiveData<List<ReceiveSMS>> = _messages
    private val userToken = loadUserToken()

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
    fun extractUrl(message: String): String? {
        val pattern = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))".toRegex()
        val matchResult = pattern.find(message)
        return matchResult?.value
    }

    private fun loadUserToken(): UserToken {
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        Log.d("auth", "loadUserToken: ${tokenId.toString()} -> ${accessToken.toString()}")
        return UserToken(tokenId, accessToken, refreshToken)
    }

    private fun saveUserToken(id: String, access_token: String, refresh_token: String) {
        val editor = sharePref.edit()
        editor.putString("token_id", id)
        editor.putString("access_token", access_token)
        editor.putString("refresh_token", refresh_token)
        editor.apply()
    }

    fun refreshToken() = viewModelScope.launch {
        val response = refreshUseCase.execute(userToken.refresh_token.toString())
        when (response) {
            is Result.Success -> {
                val account = response.data!!
                error.value = "Success"
                saveUserToken(account.userTokenResponse.id, account.userTokenResponse.accessToken, account.userTokenResponse.refreshToken)
            }
            is Result.Error -> {
                error.value = response.message
            }
        }
    }

    fun validateOrgName(org_name: String, registrarName: String): String {
        return if (org_name.isNullOrEmpty()) {
            registrarName
        } else {
            org_name
        }
    }

    fun checkOrgName(url_type: String, org_name: String, threat_type: String): String {
        return if (url_type == UNSAFE) {
            threat_type
        } else {
            org_name
        }
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

    fun deleteHistory(id: Int) = viewModelScope.launch {
        deleteHistoryUseCase.execute(id)
    }

    fun checkUrl() = liveData {
        val response = analysisUrlUseCase.execute(url.value.toString(), userToken.access_token!!)
        when (response) {
            is Result.Success -> {
                val data = response.data!!
                error.value = "Success"
                emit(data)
            }
            is Result.Error -> {
                error.value = response.message
            }
        }
    }

//    fun checkUrl() = liveData {
//        val analysisResult = analysisUrlUseCase.execute(url.value.toString(), userToken.access_token!!)
//        Log.d("analysisResult", "checkUrl: $analysisResult")
//        emit(analysisResult)
//    }

    companion object {
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "noinformation"
    }

}