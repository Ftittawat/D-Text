package com.example.d_text.presentation.notification

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.d_text.R
import com.example.d_text.databinding.FragmentNotificationBinding
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
//        binding = FragmentNotificationBinding.inflate(inflater, container,false)
//        return inflater.inflate(
//            R.layout.fragment_notification, container, false
//        )
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//        val layoutParams = bottomSheet.layoutParams
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        bottomSheet.layoutParams = layoutParams
////        val view: FrameLayout = dialog?.findViewById(R.id.notification_sheet)!!
////        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun getWindowHeight() = resources.displayMetrics.heightPixels
}