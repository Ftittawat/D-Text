package com.example.d_text.presentation.authentication.acceptpolicy

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.d_text.R
import com.example.d_text.databinding.FragmentAcceptPolicyBinding
import com.example.d_text.presentation.authentication.signup.SignUpFragment
import com.example.d_text.presentation.home.HomeActivity

class AcceptPolicyFragment : Fragment() {

    private lateinit var vm: AcceptPolicyViewModel
    private lateinit var binding: FragmentAcceptPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptPolicyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[AcceptPolicyViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.continueButton.isEnabled = false
        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        binding.checkboxAccept.setOnCheckedChangeListener { button, isChecked ->
            binding.continueButton.isEnabled = isChecked
        }
        binding.continueButton.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}