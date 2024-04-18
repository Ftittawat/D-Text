package com.senior.d_text.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
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

    }
}