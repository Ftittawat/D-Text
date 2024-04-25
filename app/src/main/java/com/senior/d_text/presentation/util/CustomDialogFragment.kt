package com.senior.d_text.presentation.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentCustomDialogBinding

class CustomDialogFragment() : DialogFragment() {

    private lateinit var binding: FragmentCustomDialogBinding

    var type: Int = 0

    private var title: String = ""
    private var description: String = ""
    private var confirmBtn: Boolean = false
    private var cancelBtn: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(layoutInflater)
        return binding.root
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //setStyle(STYLE_NORMAL, R.style.Theme_AlertDialog)
//    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupView() {
        when (type) {
            1 -> {
                binding.title.text = getText(R.string.warning_1)
                binding.description.text = getText(R.string.warning_2)
                binding.cancelButton.isInvisible = true
            }
            2 -> {
                binding.title.text = getText(R.string.warning_SDK_34_1)
                binding.description.text = getText(R.string.warning_SDK_34_2)
                binding.cancelButton.isInvisible = true
            }
            3 -> {
                binding.title.text = getText(R.string.warning_dialog_1)
                binding.description.text = getText(R.string.warning_dialog_2)
                binding.confirmButton.isInvisible = true
            }
            else -> {
                binding.title.text = title
                binding.description.text = description
                binding.confirmButton.isVisible = confirmBtn
                binding.cancelButton.isVisible = cancelBtn
            }
        }
    }

    private fun setupButton() {
        when (type) {
            1 -> {
                binding.confirmButton.setOnClickListener {
                    dismiss()
                }
                binding.cancelButton.setOnClickListener {
                    dismiss()
                }
            }
            2 -> {
                binding.confirmButton.setOnClickListener {
                    dismiss()
                }
                binding.cancelButton.setOnClickListener {
                    dismiss()
                }
            }
            3 -> {
                binding.confirmButton.setOnClickListener {
                    dismiss()
                }
                binding.cancelButton.setOnClickListener {
                    dismiss()
                }
            }
            else -> {
                binding.confirmButton.setOnClickListener {
                    dismiss()
                }
                binding.cancelButton.setOnClickListener {
                    dismiss()
                }
            }
        }
    }

    fun setTitle(text: String) {
        title = text
    }

    fun setDescription(text: String) {
        description = text
    }

    fun setConfirmButton() {
        cancelBtn = true
    }

    fun setCancelButton() {
        confirmBtn = true
    }

}