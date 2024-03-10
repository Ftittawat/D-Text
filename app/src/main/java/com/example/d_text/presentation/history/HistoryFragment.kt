package com.example.d_text.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d_text.R
import com.example.d_text.databinding.FragmentHistoryBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryFragment : BottomSheetDialogFragment() {

    private lateinit var vm: HistoryViewModel
    private lateinit var  binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val bottomSheet: View? = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        setStyle(STYLE_NORMAL, R.style.FullScreenBottomSheetDialog)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.isDraggable = false
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }
}