package com.senior.d_text.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.history.History
import com.senior.d_text.data.model.history.HistoryList
import com.senior.d_text.databinding.ItemHistoryBinding
import com.senior.d_text.presentation.core.OnItemClickListener

class HistoryAdapter(): RecyclerView.Adapter<MyViewHolder>() {

    val historyList: ArrayList<History> = ArrayList()
    private lateinit var mListener: OnItemClickListener

    fun setList(history: List<History>) {
        historyList.clear()
        historyList.addAll(history)
        historyList.reverse()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(historyList[position])
    }
}

class MyViewHolder(val binding: ItemHistoryBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun bind(history: History) {
        binding.cardTitle.text = history.url
        binding.cardDescription.text = history.type
        binding.cardDatetime.text = history.date_time
        when (history.risk_level) {
            "unsafe" -> {
                binding.unsafe.isVisible = true
                binding.suspicious.isGone = true
                binding.safe.isGone = true
                binding.noInformation.isGone = true
            }
            "suspicious" -> {
                binding.unsafe.isGone = true
                binding.suspicious.isVisible = true
                binding.safe.isGone = true
                binding.noInformation.isGone = true
            }
            "safe" -> {
                binding.unsafe.isGone = true
                binding.suspicious.isGone = true
                binding.safe.isVisible = true
                binding.noInformation.isGone = true
            }
            else -> {
                binding.unsafe.isGone = true
                binding.suspicious.isGone = true
                binding.safe.isGone = true
                binding.noInformation.isVisible = true
            }
        }
    }
}