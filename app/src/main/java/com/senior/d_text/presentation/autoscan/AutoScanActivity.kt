package com.senior.d_text.presentation.autoscan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivityAutoScanBinding
import com.senior.d_text.databinding.DialogConfirmDeleteBinding
import com.senior.d_text.presentation.autoscan.autoscanhistory.AutoScanHistoryFragment
import com.senior.d_text.presentation.core.AutoScanViewModelFactory
import com.senior.d_text.presentation.core.OnItemClickListenerDelBtn
import com.senior.d_text.presentation.di.Injector
import com.senior.d_text.presentation.notification.NotificationAdapter
import javax.inject.Inject

class AutoScanActivity: AppCompatActivity() {

    @Inject
    lateinit var factory: AutoScanViewModelFactory
    private lateinit var binding: ActivityAutoScanBinding
    private lateinit var vm: AutoScanViewModel
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutoScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createAutoScanSubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[AutoScanViewModel::class.java]
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
    }

    private fun initRecyclerView() {
        binding.autoScanRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = NotificationAdapter()
        binding.autoScanRecyclerview.adapter = adapter
        displayNotification()
        adapter.setOnItemClickListener(object : OnItemClickListenerDelBtn {
            override fun onItemClick(position: Int) {
                val autoScanHistoryFragment = AutoScanHistoryFragment()
                val bundle = Bundle()
                bundle.putSerializable("NOTIFICATION_DATA", adapter.getList(position))
                autoScanHistoryFragment.arguments = bundle
                autoScanHistoryFragment.show(supportFragmentManager, "AutoScanHistoryFragmentTag")
            }

            override fun onDeleteClick(position: Int) {
                val id = adapter.getList(position).id
                showConfirmDeleteDialog(id)
            }

        })
    }

    private fun displayNotification() {
        val responseData = vm.getNotification()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Notification Data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showConfirmDeleteDialog(id: Int) {
        val dialogBinding: DialogConfirmDeleteBinding = DialogConfirmDeleteBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this, R.style.Theme_AlertDialog)
        builder.setView(dialogBinding.root)

        val dialog = builder.create()
        dialog.show()

        dialogBinding.confirmButton.setOnClickListener {
            vm.deleteNotification(id)
            dialog.dismiss()
            Toast.makeText(applicationContext, getText(R.string.delete_success), Toast.LENGTH_SHORT).show()
        }
        dialogBinding.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_right, R.anim.animate_slide_out_left)
    }
}