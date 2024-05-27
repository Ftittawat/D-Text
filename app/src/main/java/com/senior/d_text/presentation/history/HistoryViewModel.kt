package com.senior.d_text.presentation.history

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.senior.d_text.R

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    val url = MutableLiveData("")
    val riskLevel = MutableLiveData("")
    val dTextLevel = MutableLiveData("")
    val googleLevel = MutableLiveData("")
    val type = MutableLiveData("")
    val dateTime = MutableLiveData("")
    val domainAgeDay = MutableLiveData(0)
    val hasForm = MutableLiveData(false)
    val hasIframe = MutableLiveData(false)
    val hasShortened = MutableLiveData(false)
    val hasSsl = MutableLiveData(false)
    val urlScore = MutableLiveData(0.0)
    val redirectUrl = MutableLiveData("")

    fun validationUrl(value: String): String {
        return if (!value.startsWith("http://") && !value.startsWith("https://")) {
            if (!value.startsWith("www.")) {
                "https://www.$value"
            }
            else {
                "https://$value"
            }
        }
        else {
            value
        }
    }

    fun validationLevel(level: Boolean): String {
        return if (level) {
            getString(R.string.found)
        } else {
            getString(R.string.not_found)
        }
    }

    fun validationRedirectUrl(url: String): String {
        return if (url == "false") {
            getString(R.string.no_information)
        } else {
            url
        }
    }

    private fun getString(resId: Int): String {
        val context: Context = getApplication<Application>().applicationContext
        return context.getString(resId)
    }

    companion object {
        const val UNSAFE = "unsafe"
        const val SUSPICIOUS = "suspicious"
        const val SAFE = "safe"
        const val NO_INFORMATION = "no_information"
        const val TRUE = "true"
        const val FALSE = "false"
    }
}