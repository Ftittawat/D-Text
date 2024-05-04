package com.senior.d_text.presentation.authentication.signin

import android.content.Context
import android.content.Intent
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
import com.senior.d_text.databinding.FragmentSignInBinding
import com.senior.d_text.presentation.authentication.acceptpolicy.AcceptPolicyFragment
import com.senior.d_text.presentation.authentication.signup.SignUpFragment
import com.senior.d_text.presentation.core.SignInViewModelFactory
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.home.HomeActivity
import com.senior.d_text.presentation.util.CustomDialogFragment
import javax.inject.Inject

class SignInFragment() : Fragment() {

    @Inject
    lateinit var factory: SignInViewModelFactory
    private lateinit var vm: SignInViewModel
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as Injector).createSignInSubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[SignInViewModel::class.java]
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
        emailWatcher { validateEmail() }
        passwordWatcher { validatePassword() }

        vm.email.observe(this) {

        }
        vm.password.observe(this) {

        }
        vm.errorEmail.observe(this) {
            binding.emailLayout.error = vm.errorEmail.value
            binding.emailLayout.errorContentDescription = vm.errorEmail.value
            validateButton()
        }
        vm.errorPassword.observe(this) {
            binding.passwordLayout.error = vm.errorPassword.value
            binding.passwordLayout.errorContentDescription = vm.errorPassword.value
            validateButton()
        }
//        vm.error.observe(this) {
//            if (vm.error.value.isNullOrEmpty()) {
//                binding.error.isGone = true
//            }
//            else {
//                binding.error.isVisible = true
//                binding.error.text = vm.error.value
//            }
//        }
        vm.error.observe(this) { error ->
            if (error == "Success") {
                binding.error.isGone = true
                Toast.makeText(context, getText(R.string.signin_success), Toast.LENGTH_LONG).show()
                if (vm.checkPolicy()) {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                    activity?.overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
                }
                else {
                    val fragment = AcceptPolicyFragment()
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, fragment)
                        ?.commit()
                }
            }
            else if (error == "incorrect password") {
                binding.error.isGone = true
                vm.errorPassword.value = getString(R.string.error_incorrect_password)
            }
            else if (error == "record not found") {
                binding.error.isGone = true
                vm.errorEmail.value = getString(R.string.error_username_1)
            }
            else {
                binding.error.isGone = true
            }
        }
    }

    private fun setupButton() {
        binding.signInFragment.setOnClickListener {
            it.hideKeyboard()
            validateEmail()
            validatePassword()
        }
        binding.signInContainer.setOnClickListener {
            it.hideKeyboard()
            validateEmail()
            validatePassword()
        }
        binding.noAccount.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, SignUpFragment())
                ?.commit()
        }
        binding.signInButton.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fragment_container, AcceptPolicyFragment())
//                ?.addToBackStack(null)
//                ?.commit()
            it.hideKeyboard()
            vm.signInWithUsername()
//            Log.d("auth", "setupButton: ${vm.error.value.toString()}")
//            Log.d("auth", "setupButton: ${vm.error.value.isNullOrEmpty()}")
//            if (vm.error.value.isNullOrEmpty()) {
//                val intent = Intent(activity, HomeActivity::class.java)
//                // intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//                startActivity(intent)
//                activity?.overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit)
//            }
        }
        binding.forgotPassword.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fragment_container, ResetPasswordFirstStepFragment())
//                ?.addToBackStack(null)
//                ?.commit()
            val customDialogFragment = CustomDialogFragment()
//            customDialogFragment.type = 3
            customDialogFragment.setTitle(getString(R.string.warning_dialog_1))
            customDialogFragment.setDescription(getString(R.string.warning_dialog_2))
            customDialogFragment.setConfirmButton()
            customDialogFragment.show(activity?.supportFragmentManager!!, "CustomDialogFragment")
        }
        binding.googleAccount.setOnClickListener {

        }
        binding.signInButton.isEnabled = false
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

    private fun validateEmail() {
        vm.email.value = binding.email.text.toString()
        vm.errorEmail.value = vm.validationEmail(binding.email.text.toString())
    }

    private fun validatePassword() {
        vm.password.value = binding.password.text.toString()
        vm.errorPassword.value = vm.validationPassword(binding.password.text.toString())
    }

    private fun validateButton() {
        binding.signInButton.isEnabled = vm.errorEmail.value.isNullOrEmpty() && vm.errorPassword.value.isNullOrEmpty() && !vm.email.value.isNullOrEmpty() && !vm.password.value.isNullOrEmpty()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}