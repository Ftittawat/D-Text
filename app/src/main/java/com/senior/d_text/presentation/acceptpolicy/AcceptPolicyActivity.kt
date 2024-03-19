package com.senior.d_text.presentation.acceptpolicy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.senior.d_text.databinding.ActivityAcceptPolicyBinding
import com.senior.d_text.presentation.home.HomeActivity
import javax.inject.Inject

class AcceptPolicyActivity : AppCompatActivity() {

    @Inject
    private lateinit var binding: ActivityAcceptPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceptPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.continueButton.isEnabled = false
        binding.backButton.setOnClickListener {
            this.finish()
        }
        binding.checkboxAccept.setOnCheckedChangeListener { button, isChecked ->
            binding.continueButton.isEnabled = isChecked
        }
        binding.continueButton.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}