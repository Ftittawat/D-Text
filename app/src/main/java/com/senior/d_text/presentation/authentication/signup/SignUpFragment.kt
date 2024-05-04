package com.senior.d_text.presentation.authentication.signup

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentSignUpBinding
import com.senior.d_text.presentation.authentication.acceptpolicy.AcceptPolicyFragment
import com.senior.d_text.presentation.authentication.signin.SignInFragment
import com.senior.d_text.presentation.core.SignUpViewModelFactory
import com.senior.d_text.presentation.di.Injector
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var factory: SignUpViewModelFactory
    private lateinit var vm: SignUpViewModel
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as Injector).createSignUpSubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[SignUpViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupView() {
        usernameWatcher { validateUsername() }
        emailWatcher { validateEmail() }
        phoneWatcher { validatePhone() }
        passwordWatcher { validatePassword() }
        confirmPasswordWatcher { validateConfirmPassword() }

        vm.username.observe(this) {

        }
        vm.email.observe(this) {

        }
        vm.phone.observe(this) {

        }
        vm.password.observe(this) {

        }
        vm.confirmPassword.observe(this) {

        }
        vm.errorUsername.observe(this) {
            binding.usernameLayout.error = vm.errorUsername.value
            binding.usernameLayout.errorContentDescription = vm.errorUsername.value
            validateButton()
        }
        vm.errorEmail.observe(this) {
            binding.emailLayout.error = vm.errorEmail.value
            binding.emailLayout.errorContentDescription = vm.errorEmail.value
            validateButton()
        }
        vm.errorPhone.observe(this) {
            binding.phoneLayout.error = vm.errorPhone.value
            binding.phoneLayout.errorContentDescription = vm.errorPhone.value
            validateButton()
        }
        vm.errorPassword.observe(this) {
            binding.passwordLayout.error = vm.errorPassword.value
            binding.passwordLayout.errorContentDescription = vm.errorPassword.value
            validateButton()
        }
        vm.errorConfirmPassword.observe(this) {
            binding.confirmPasswordLayout.error = vm.errorConfirmPassword.value
            binding.confirmPasswordLayout.errorContentDescription = vm.errorConfirmPassword.value
            validateButton()
        }
        vm.error.observe(this) { error ->
            if (error == "Success") {
                binding.error.isGone = true
                Toast.makeText(context, getText(R.string.signup_success), Toast.LENGTH_LONG).show()
                val fragment = AcceptPolicyFragment()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container, fragment)
                    ?.commit()
            }
            else if (error == "This email has been used.") {
                binding.error.isGone = true
                vm.errorEmail.value = getString(R.string.error_email_1)
            }
            else if (error == "This username has been used.") {
                binding.error.isGone = true
                vm.errorUsername.value = getString(R.string.error_username_2)
            }
            else {
                binding.error.isGone = true
            }
        }
    }

    private fun setupButton() {
        binding.signUpFragment.setOnClickListener {
            it.hideKeyboard()
            validateUsername()
            validateEmail()
            validatePhone()
            validatePassword()
            validateConfirmPassword()
        }
        binding.signUpContainer.setOnClickListener {
            it.hideKeyboard()
            validateUsername()
            validateEmail()
            validatePhone()
            validatePassword()
            validateConfirmPassword()
        }
        binding.haveAccount.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, SignInFragment())
                ?.commit()
        }
        binding.signUpButton.setOnClickListener {
//            val fragment = AcceptPolicyFragment()
//            val bundle = Bundle()
//            bundle.putString("key", "value")
//            fragment.arguments
//
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fragment_container, fragment)
//                ?.commit()
            it.hideKeyboard()
            vm.signUpWithUsername()
        }
        binding.googleAccount.setOnClickListener {
            
        }
        binding.signUpButton.isEnabled = false
    }

    private fun usernameWatcher(func: () -> Unit) {
        binding.username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                func()
            }

        })
    }

    private fun emailWatcher(func: () -> Unit) {
        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                func()
            }
        })
    }

    private fun phoneWatcher(func: () -> Unit) {
        binding.phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                func()
            }
        })
    }

    private fun passwordWatcher(func: () -> Unit) {
        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                func()
            }
        })
    }

    private fun confirmPasswordWatcher(func: () -> Unit) {
        binding.confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                func()
            }
        })
    }

    private fun validateUsername() {
        vm.username.value = binding.username.text.toString()
        vm.errorUsername.value = vm.validationUserName(binding.username.text.toString())
    }

    private fun validateEmail() {
        vm.email.value = binding.email.text.toString()
        vm.errorEmail.value = vm.validationEmail(binding.email.text.toString())
    }

    private fun validatePhone() {
        vm.phone.value = binding.phone.text.toString()
        vm.errorPhone.value = vm.validationPhone(binding.phone.text.toString())
    }

    private fun validatePassword() {
        vm.password.value = binding.password.text.toString()
        vm.errorPassword.value = vm.validationPassword(binding.password.text.toString())
    }

    private fun validateConfirmPassword() {
        vm.confirmPassword.value = binding.confirmPassword.text.toString()
        vm.errorConfirmPassword.value = vm.validationConfirmPassword(binding.confirmPassword.text.toString(), binding.password.text.toString())
    }

    private fun validateButton() {
        binding.signUpButton.isEnabled =
            vm.errorUsername.value.isNullOrEmpty() &&
                    vm.errorEmail.value.isNullOrEmpty() &&
                    vm.errorPhone.value.isNullOrEmpty() &&
                    vm.errorPassword.value.isNullOrEmpty() &&
                    vm.errorConfirmPassword.value.isNullOrEmpty() &&
                    !vm.username.value.isNullOrEmpty() &&
                    !vm.email.value.isNullOrEmpty() &&
                    !vm.phone.value.isNullOrEmpty() &&
                    !vm.password.value.isNullOrEmpty() &&
                    !vm.confirmPassword.value.isNullOrEmpty()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}