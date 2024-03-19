package com.senior.d_text.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(): ViewModel() {

    var url = MutableLiveData("")

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

}