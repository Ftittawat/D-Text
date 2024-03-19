package com.senior.d_text.presentation.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senior.d_text.R
import com.senior.d_text.databinding.FragmentNotificationBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class NotificationFragment : BottomSheetDialogFragment() {

    @Inject
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(layoutInflater)
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
//            behavior.isDraggable = false
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
//        val data_1 = arguments?.getString("test")
//        val data_2 = arguments?.getInt("test2")
//        binding.notificationText.text = data_1
//        binding.notificationText2.text = data_2.toString()
    }
}