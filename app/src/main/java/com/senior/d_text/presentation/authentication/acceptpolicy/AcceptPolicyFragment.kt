package com.senior.d_text.presentation.authentication.acceptpolicy

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentAcceptPolicyBinding
import com.senior.d_text.presentation.home.HomeActivity

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
            vm.policyAccept()
            val intent = Intent(activity, HomeActivity::class.java)
            // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
        }
    }

}