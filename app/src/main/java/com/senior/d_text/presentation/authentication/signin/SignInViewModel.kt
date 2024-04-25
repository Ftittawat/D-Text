package com.senior.d_text.presentation.authentication.signin

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.senior.d_text.R

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    val email = MutableLiveData("")
    val errorEmail = MutableLiveData("")
    val password = MutableLiveData("")
    val errorPassword = MutableLiveData("")

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    fun validationEmail(text: String): String {
        val regexEmail = android.util.Patterns.EMAIL_ADDRESS
        return when {
            text.isEmpty() -> getString(R.string.error_empty_email)
            !regexEmail.matcher(text).matches() -> getString(R.string.error_format)
            else -> ""
        }
    }

    fun validationPassword(text: String): String {
        return when {
            text.isEmpty() -> getString(R.string.error_empty_password)
            else -> ""
        }
    }

//    fun validationPassword(text: String) : String {
//
//    }

    private fun getString(resId: Int): String {
        val context: Context = getApplication<Application>().applicationContext
        return context.getString(resId)
    }

    private fun saveToken() {
        val editor = sharePref.edit()
        editor.putString("token", "")
        editor.apply()
    }

    private fun loadToken(): String? {
        return sharePref.getString("token", "")
    }
}