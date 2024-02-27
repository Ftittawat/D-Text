package com.example.d_text.presentation.authentication.resetpassword.second_step

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R
import com.example.d_text.databinding.FragmentResetPasswordSecondStepBinding
import com.example.d_text.presentation.authentication.resetpassword.third_step.ResetPasswordThirdStepFragment

class ResetPasswordSecondStepFragment : Fragment() {

    private lateinit var vm: ResetPasswordSecondStepViewModel
    private lateinit var binding: FragmentResetPasswordSecondStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordSecondStepBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        vm = ViewModelProvider(this).get(ResetPasswordSecondStepViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.continueButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ResetPasswordThirdStepFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}