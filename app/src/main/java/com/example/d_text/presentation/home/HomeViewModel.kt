package com.example.d_text.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(): ViewModel() {

    val staffName = MutableLiveData("")
    val errorStaffName = MutableLiveData("")

}