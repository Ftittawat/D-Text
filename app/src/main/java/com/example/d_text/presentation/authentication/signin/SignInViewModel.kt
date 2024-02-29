package com.example.d_text.presentation.authentication.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    val email = MutableLiveData("")
    val errorEmail = MutableLiveData("")
    val password = MutableLiveData("")
    val errorPassword = MutableLiveData("")

    fun validationEmail(text: String): String {
        val regexEmail = android.util.Patterns.EMAIL_ADDRESS
        return when (text.isEmpty()) {
            true -> "กรุณาระบุข้อมูล"
            else -> ""
        }
    }

    companion object {
        const val EMPTY_INLINE_ERROR = "กรุณาระบุข้อมูล"
    }
}