package com.example.d_text.presentation.authentication.resetpassword.third_step

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R
import com.example.d_text.databinding.FragmentResetPasswordThirdStepBinding

class ResetPasswordThirdStepFragment : Fragment() {

    private lateinit var vm: ResetPasswordThirdStepViewModel
    private lateinit var binding: FragmentResetPasswordThirdStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordThirdStepBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        vm = ViewModelProvider(this).get(ResetPasswordThirdStepViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        setupBotton()
    }

    private fun setupBotton() {
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.resetButton.setOnClickListener {

        }
    }

}