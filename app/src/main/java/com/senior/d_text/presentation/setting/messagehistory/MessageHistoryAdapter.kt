package com.senior.d_text.presentation.setting.messagehistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.message.Message
import com.senior.d_text.data.model.message.ReceiveSMS
import com.senior.d_text.databinding.ItemMessageHistoryBinding

class MessageHistoryAdapter(): RecyclerView.Adapter<MessageHistoryViewHolder>() {

    private val messageHistoryList: ArrayList<Message> = ArrayList()

    fun setList(sms: List<Message>) {
        messageHistoryList.clear()
        messageHistoryList.addAll(sms)
        messageHistoryList.reverse()
    }

    fun getList(position: Int): Message {
        return messageHistoryList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMessageHistoryBinding.inflate(layoutInflater, parent, false)
        return MessageHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messageHistoryList.size
    }

    override fun onBindViewHolder(holder: MessageHistoryViewHolder, position: Int) {
        holder.bind(messageHistoryList[position])
    }
}

class MessageHistoryViewHolder(
    val binding: ItemMessageHistoryBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(message: Message) {
        binding.cardTitle.text = message.sender
        binding.cardDescription.text = message.text
        binding.cardDatetime.text = message.date_time
    }
}