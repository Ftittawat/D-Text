package com.senior.d_text.presentation.setting.messagehistory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.senior.d_text.R
import com.senior.d_text.databinding.ActivitySettingMessageHistoryBinding
import com.senior.d_text.presentation.core.MessageHistoryViewModelFactory
import com.senior.d_text.presentation.di.Injector
import javax.inject.Inject

class MessageHistoryActivity(): AppCompatActivity() {

    @Inject
    lateinit var factory: MessageHistoryViewModelFactory
    private lateinit var binding: ActivitySettingMessageHistoryBinding
    private lateinit var vm: MessageHistoryViewModel
    private lateinit var adapter: MessageHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingMessageHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as Injector).createMessageHistorySubComponent()
            .inject(this)
        vm = ViewModelProvider(this, factory)[MessageHistoryViewModel::class.java]
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        setupButton()
    }

    private fun setupButton() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.deleteButton.setOnClickListener {
            vm.deleteAllMessageHistory()
        }
    }

    private fun initRecyclerView() {
        binding.messageHistoryRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MessageHistoryAdapter()
        binding.messageHistoryRecyclerview.adapter = adapter
        displayMessageHistory()
    }

    private fun displayMessageHistory() {
        val responseData = vm.getMessageHistory()
        responseData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Message History Data" ,Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
    }
}