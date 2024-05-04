package com.senior.d_text.presentation.setting.notificationhistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.notification.ReceiveNotification
import com.senior.d_text.databinding.ItemNotificationHistoryBinding

class NotificationHistoryAdapter: RecyclerView.Adapter<NotificationNotificationViewHolder>() {

    private val notificationHistoryList: ArrayList<ReceiveNotification> = ArrayList()

    fun setList(notification: List<ReceiveNotification>) {
        notificationHistoryList.clear()
        notificationHistoryList.addAll(notification)
        notificationHistoryList.reverse()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotificationNotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationHistoryBinding.inflate(layoutInflater, parent, false)
        return NotificationNotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notificationHistoryList.size
    }

    override fun onBindViewHolder(holder: NotificationNotificationViewHolder, position: Int) {
        holder.bind(notificationHistoryList[position])
    }

}

class NotificationNotificationViewHolder(
    val binding: ItemNotificationHistoryBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(notification: ReceiveNotification) {
        binding.cardTitle.text = notification.appName
        binding.cardDescription1.text = notification.title
        binding.cardDescription2.text = notification.text
        binding.cardDatetime.text = notification.dateTime
    }
}

