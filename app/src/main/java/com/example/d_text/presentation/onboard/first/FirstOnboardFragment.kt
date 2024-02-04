package com.example.d_text.presentation.onboard.first

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R

class FirstOnboardFragment : Fragment() {

    companion object {
        fun newInstance() = FirstOnboardFragment()
    }

    private lateinit var viewModel: FirstOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_onboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirstOnboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}