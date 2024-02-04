package com.example.d_text.presentation.onboard.third

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R

class ThirdOnboardFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdOnboardFragment()
    }

    private lateinit var viewModel: ThirdOnboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_onboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThirdOnboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}