package com.example.d_text.presentation.SignUp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.d_text.databinding.ActivitySignupBinding
import com.example.d_text.presentation.SignIn.SignInActivity
import com.example.d_text.presentation.core.SignUpViewModelFactory
import javax.inject.Inject

class SignUpActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SignUpViewModelFactory
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        signUpViewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.bottomContainer.setOnClickListener {
            it.hideKeyboard()
        }

        binding.closeButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}