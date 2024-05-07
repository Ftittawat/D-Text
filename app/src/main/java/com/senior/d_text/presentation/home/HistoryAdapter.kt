package com.senior.d_text.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.history.History
import com.senior.d_text.databinding.ItemHistoryBinding
import com.senior.d_text.presentation.core.OnItemClickListener
import com.senior.d_text.presentation.core.OnItemClickListenerDelBtn

class HistoryAdapter(): RecyclerView.Adapter<HistoryViewHolder>() {

    private val historyList: ArrayList<History> = ArrayList()
    private lateinit var mListener: OnItemClickListenerDelBtn

    fun setList(history: List<History>) {
        historyList.clear()
        historyList.addAll(history)
        historyList.reverse()
    }

    fun getList(position: Int): History{
        return historyList[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListenerDelBtn) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        return HistoryViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }
}

class HistoryViewHolder(val binding: ItemHistoryBinding, listener: OnItemClickListenerDelBtn): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        binding.deleteButton.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onDeleteClick(position)
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