package com.senior.d_text.presentation.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.databinding.ItemNotificationBinding
import com.senior.d_text.presentation.core.OnItemClickListener

class NotificationAdapter(): RecyclerView.Adapter<NotificationViewHolder>() {

    private val notificationList: ArrayList<Notification> = ArrayList()
    private lateinit var mListener: OnItemClickListener

    fun setList(notification: List<Notification>) {
        notificationList.clear()
        notificationList.addAll(notification)
        notificationList.reverse()
    }

    fun getList(position: Int): Notification {
        return notificationList[position]
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(layoutInflater, parent, false)
        return NotificationViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

}

class NotificationViewHolder(val binding: ItemNotificationBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    fun bind(notification: Notification) {
        binding.title.text = notification.url
        binding.location.text = notification.application
        binding.appType.text = notification.source
        binding.time.text = notification.date_time
        when (notification.risk_level) {
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