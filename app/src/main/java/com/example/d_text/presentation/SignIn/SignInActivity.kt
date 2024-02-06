package com.example.d_text.presentation.SignIn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.d_text.databinding.ActivitySigninBinding
import com.example.d_text.presentation.SignUp.SignUpActivity
import javax.inject.Inject

class SignInActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SignInViewModelFactory
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)

        binding.signInButton.setOnClickListener {

        }

        binding.noAccountButton.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.email.setOnFocusChangeListener { view, b ->
            if (b) {
                binding.logoContainer.isGone = true
            }
            else {
                binding.logoContainer.isVisible = true
            }

        }
    }
}