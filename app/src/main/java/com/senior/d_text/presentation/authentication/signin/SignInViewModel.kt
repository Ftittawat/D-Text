package com.senior.d_text.presentation.authentication.signin

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.senior.d_text.R
import com.senior.d_text.data.model.Result
import com.senior.d_text.domain.usecase.SignInUseCase
import kotlinx.coroutines.launch
import java.security.MessageDigest

class SignInViewModel (
    private val application: Application,
    private val signInUseCase: SignInUseCase
) : AndroidViewModel(application) {

    val email = MutableLiveData("")
    val errorEmail = MutableLiveData("")
    val password = MutableLiveData("")
    val errorPassword = MutableLiveData("")
    val error = MutableLiveData("")

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    fun validationEmail(text: String): String {
        val regexEmail = android.util.Patterns.EMAIL_ADDRESS
        return when {
            text.isEmpty() -> getString(R.string.error_empty_email)
            //!regexEmail.matcher(text).matches() -> getString(R.string.error_format)
            else -> ""
        }
    }

    fun validationPassword(text: String): String {
        return when {
            text.isEmpty() -> getString(R.string.error_empty_password)
            else -> ""
        }
    }

    fun signInWithUsername() = viewModelScope.launch {
        Log.d("auth", "Username: ${email.value} Password: ${password.value}")
        val passwordEncrypt = encryptPassword(password.value.toString())
        Log.d("auth", "passwordEncrypt: $passwordEncrypt")
        val response = signInUseCase.execute(email.value.toString(), password.value.toString())
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

    private fun encryptPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun getString(resId: Int): String {
        val context: Context = getApplication<Application>().applicationContext
        return context.getString(resId)
    }

    private fun saveToken(token: String) {
        val editor = sharePref.edit()
        editor.putString("token", token)
        editor.apply()
    }

    private fun saveUserToken(id: String, access_token: String, refresh_token: String) {
        val editor = sharePref.edit()
        editor.putString("token_id", id)
        editor.putString("access_token", access_token)
        editor.putString("refresh_token", refresh_token)
        editor.apply()
    }

    fun loadToken(): String? {
        return sharePref.getString("token", null)
    }

    fun checkPolicy(): Boolean {
        return sharePref.getBoolean("policy", false)
    }
}