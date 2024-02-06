package com.example.d_text.presentation.onboardingscreen.second

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R

class SecondOnboardFragment : Fragment() {

    companion object {
        fun newInstance() = SecondOnboardFragment()
    }

    private lateinit var viewModel: SecondOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_onboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecondOnboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}