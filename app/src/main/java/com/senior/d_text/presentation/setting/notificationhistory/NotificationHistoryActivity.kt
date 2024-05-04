package com.senior.d_text.presentation.setting.notificationhistory

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingNotificationHistoryBinding
import com.senior.d_text.databinding.DialogConfirmDeleteBinding
import com.senior.d_text.presentation.core.NotificationHistoryViewModelFactory
import com.senior.d_text.presentation.di.Injector
import javax.inject.Inject

class NotificationHistoryActivity(): AppCompatActivity() {

    @Inject
    lateinit var factory: NotificationHistoryViewModelFactory
    private lateinit var binding: ActivitySettingNotificationHistoryBinding
    private lateinit var vm: NotificationHistoryViewModel
    private lateinit var adapter: NotificationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingNotificationHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createNotificationHistorySubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[NotificationHistoryViewModel::class.java]
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.clearHistoryButton.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun initRecyclerView() {
        binding.notificationHistoryRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = NotificationHistoryAdapter()
        binding.notificationHistoryRecyclerview.adapter = adapter
        displayNotificationHistory()
    }

    private fun displayNotificationHistory() {
        val responseData = vm.getNotificationHistory()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Notification History Data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showConfirmDialog() {
        val dialogBinding: DialogConfirmDeleteBinding =
            DialogConfirmDeleteBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this, R.style.Theme_AlertDialog)
        builder.setView(dialogBinding.root)

        val dialog = builder.create()
        dialog.show()

        dialogBinding.confirmButton.setOnClickListener {
            vm.deleteAllNotificationHistory()
            dialog.dismiss()
        }
        dialogBinding.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }
}