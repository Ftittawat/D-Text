package com.example.d_text.presentation.authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d_text.R
import com.example.d_text.databinding.ActivityAuthenticationBinding
import com.example.d_text.databinding.FragmentSignInBinding
import com.example.d_text.presentation.authentication.signin.SignInFragment
import com.example.d_text.presentation.authentication.signup.SignUpFragment
import com.example.d_text.presentation.core.AuthenticationViewModelFactory
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AuthenticationViewModelFactory
    private lateinit var vm: AuthenticationViewModel
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupView()
    }

    private fun setupView() {
        val page = intent.extras?.getBoolean("sign_in")
        if (page == true) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignInFragment())
                .commit()
        } else (
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .commit()
        )
    }
}