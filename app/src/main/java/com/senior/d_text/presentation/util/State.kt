package com.senior.d_text.presentation.util

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.senior.d_text.databinding.ActivityHomeBinding

interface State {
    fun handleState(stateContext: StateContext)
}

class GuestState: State {
    override fun handleState(stateContext: StateContext) {
        stateContext.autoScanHistory(false)
    }

}

class LoggedInState: State {
    override fun handleState(stateContext: StateContext) {
        stateContext.autoScanHistory(true)
    }
}

class StateContext() {

    private lateinit var binding: ActivityHomeBinding

    private var state: State = GuestState()

    fun setState(newState: State) {
        state = newState
    }

    fun handleState() {
        state.handleState(this)
    }

    fun autoScanHistory(show: Boolean) {
        if (show) {
            binding.detectHistoryLayout.isVisible = true
        }
        else {
            binding.detectHistoryLayout.isGone = true
        }
    }
}