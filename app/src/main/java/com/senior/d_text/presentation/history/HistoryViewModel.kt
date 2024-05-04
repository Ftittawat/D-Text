package com.senior.d_text.presentation.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {

    val url = MutableLiveData("")
    val riskLevel = MutableLiveData("")
    val urlType = MutableLiveData("")
    val orgName = MutableLiveData("")
    val dateTime = MutableLiveData("")
}