package com.example.d_text.presentation.authentication.signup

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.d_text.R

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    val email = MutableLiveData("")
    val errorEmail = MutableLiveData("")
    val phone = MutableLiveData("")
    val errorPhone = MutableLiveData("")
    val password = MutableLiveData("")
    val errorPassword = MutableLiveData("")
    val confirmPassword = MutableLiveData("")
    val errorConfirmPassword = MutableLiveData("")

    fun validationEmail(text: String): String {
        val regexEmail = android.util.Patterns.EMAIL_ADDRESS
        return when {
            text.isEmpty() -> getString((R.string.error_empty_email))
            !regexEmail.matcher(text).matches() -> getString(R.string.error_format)
            else -> ""
        }
    }

    fun validationPhone(text: String): String {
        val regexPhone = android.util.Patterns.PHONE
        val regexThaiPhone = REGEX_THAI_PHONE
        return when {
            text.isEmpty() -> getString(R.string.error_empty_phone)
            !regexThaiPhone.matches(text) -> getString(R.string.error_format_phone)
            else -> ""
        }
    }

    fun validationPassword(text: String): String {
        val regexPassword = REGEX_PASSWORD
        val regexPasswordUppercase = REGEX_PASSWORD_UPPERCASE
        val regexPasswordLowercase = REGEX_PASSWORD_LOWERCASE
        val regexPasswordOneDigit = REGEX_PASSWORD_ONE_DIGIT
        val regexPasswordSpecialCharacter = REGEX_PASSWORD_SPECIAL_CHARACTER
        val regexPasswordNoWhiteSpace = REGEX_PASSWORD_NO_WHITESPACE
        val regexPasswordMin = REGEX_PASSWORD_MIN
        return when {
            text.isEmpty() -> getString(R.string.error_empty_password)
            // !regexPassword.matches(text) -> "Password Not Secure"
            !regexPasswordUppercase.containsMatchIn(text) -> getString(R.string.error_password_uppercase)
            !regexPasswordLowercase.containsMatchIn(text) ->  getString(R.string.error_password_lowercase)
            !regexPasswordOneDigit.containsMatchIn(text) -> getString(R.string.error_password_digit)
            !regexPasswordSpecialCharacter.containsMatchIn(text) -> getString(R.string.error_password_special_character)
            !regexPasswordNoWhiteSpace.containsMatchIn(text) -> getString(R.string.error_password_whitespace)
            !regexPasswordMin.containsMatchIn(text) -> getString(R.string.error_password_min)
            else -> ""
        }
    }

    fun validationConfirmPassword(text: String, confirm: String): String {
        return when {
            text.isEmpty() -> getString(R.string.error_empty_password)
            text != confirm -> getString(R.string.error_notmatch_password)
            else -> ""
        }
    }

    private fun getString(resId: Int): String {
        val context: Context = getApplication<Application>().applicationContext
        return context.getString(resId)
    }

    companion object {
        val REGEX_THAI_PHONE = Regex("^(0[689])+([0-9]{8})+$")
        val REGEX_PASSWORD = Regex("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&_+=!])(?=\\S+\$).{8,}")
        val REGEX_PASSWORD_UPPERCASE = Regex("(?=.*[A-Z])")
        val REGEX_PASSWORD_LOWERCASE = Regex("(?=.*[a-z])")
        val REGEX_PASSWORD_ONE_DIGIT = Regex("(?=.*\\d)")
        val REGEX_PASSWORD_SPECIAL_CHARACTER = Regex("(?=.*[@#\$%^&_+=!])")
        val REGEX_PASSWORD_NO_WHITESPACE = Regex("(?=\\S+\$)")
        val REGEX_PASSWORD_MIN = Regex(".{8,}")
    }
}