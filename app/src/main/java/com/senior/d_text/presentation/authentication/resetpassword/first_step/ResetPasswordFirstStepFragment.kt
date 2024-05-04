package com.senior.d_text.presentation.authentication.resetpassword.first_step

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentResetPasswordFirstStepBinding
import com.senior.d_text.presentation.authentication.resetpassword.second_step.ResetPasswordSecondStepFragment

class ResetPasswordFirstStepFragment : Fragment() {

    private lateinit var vm: ResetPasswordFirstStepViewModel
    private lateinit var binding: FragmentResetPasswordFirstStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordFirstStepBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[ResetPasswordFirstStepViewModel::class.java]
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
                ?.replace(R.id.fragment_container, ResetPasswordSecondStepFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}