package com.example.d_text.presentation.authentication.signin

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.d_text.R
import com.example.d_text.databinding.FragmentSignInBinding
import com.example.d_text.presentation.authentication.acceptpolicy.AcceptPolicyFragment
import com.example.d_text.presentation.authentication.resetpassword.first_step.ResetPasswordFirstStepFragment
import com.example.d_text.presentation.authentication.signup.SignUpFragment

class SignInFragment : Fragment() {

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
//        vm = ViewModelProvider(this).get(SignInViewModel::class.java)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        vm = ViewModelProvider(this).get(SignInViewModel::class.java)
//    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.signInFragment.setOnClickListener {
            it.hideKeyboard()
        }
        binding.noAccount.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, SignUpFragment())
                ?.commit()
        }
        binding.signInButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, AcceptPolicyFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.forgotPassword.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ResetPasswordFirstStepFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}