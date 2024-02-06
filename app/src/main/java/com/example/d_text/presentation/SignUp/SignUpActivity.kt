package com.example.d_text.presentation.SignUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.d_text.databinding.ActivitySignupBinding
import com.example.d_text.presentation.SignIn.SignInActivity
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

        binding.signUpButton.setOnClickListener {

        }

        binding.haveAccountButton.setOnClickListener {
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}