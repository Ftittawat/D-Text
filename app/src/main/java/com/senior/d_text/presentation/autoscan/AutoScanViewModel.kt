package com.senior.d_text.presentation.autoscan

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.senior.d_text.data.model.Result
import com.senior.d_text.data.model.authentication.UserToken
import com.senior.d_text.domain.usecase.AnalysisUrlUseCase

class AutoScanViewModel(
    private val application: Application,
    private val analysisUrlUseCase: AnalysisUrlUseCase
): AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    private val userToken = loadUserToken()
    var error: String = ""

    fun checkUrl(url: String) = liveData {
        val response = analysisUrlUseCase.execute(url, userToken.access_token.toString())
        when (response) {
            is Result.Success -> {
                val data = response.data!!
                error = "Success"
                emit(data)
            }
            is Result.Error -> {
                error = response.message
            }
        }
    }

    private fun loadUserToken(): UserToken {
        val tokenId = sharePref.getString("token_id", "")
        val accessToken = sharePref.getString("access_token", "")
        val refreshToken = sharePref.getString("refresh_token", "")
        Log.d("auth", "loadUserToken: ${tokenId.toString()} -> ${accessToken.toString()}")
        return UserToken(tokenId, accessToken, refreshToken)
    }

}