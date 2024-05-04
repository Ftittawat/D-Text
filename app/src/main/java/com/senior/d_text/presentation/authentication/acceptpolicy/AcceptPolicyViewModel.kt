package com.senior.d_text.presentation.authentication.acceptpolicy

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class AcceptPolicyViewModel(application: Application) : AndroidViewModel(application) {

    private val sharePref = application.getSharedPreferences("account", Context.MODE_PRIVATE)

    fun policyAccept() {
        val editor = sharePref.edit()
        editor.putBoolean("policy", true)
        editor.apply()
    }

    fun checkPolicy(): Boolean {
        return sharePref.getBoolean("policy", false)
    }
}