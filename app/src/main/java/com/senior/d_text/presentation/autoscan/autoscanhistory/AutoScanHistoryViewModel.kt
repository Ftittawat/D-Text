package com.senior.d_text.presentation.autoscan.autoscanhistory

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.senior.d_text.R

class AutoScanHistoryViewModel(application: Application): AndroidViewModel(application) {

    val url = MutableLiveData("")
    val application = MutableLiveData("")
    val source = MutableLiveData("")
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

    private fun getString(resId: Int): String {
        val context: Context = getApplication<Application>().applicationContext
        return context.getString(resId)
    }
}