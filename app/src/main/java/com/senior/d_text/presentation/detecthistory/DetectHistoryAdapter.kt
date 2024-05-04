package com.senior.d_text.presentation.detecthistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senior.d_text.data.model.notification.Notification
import com.senior.d_text.databinding.ItemNotificationBinding
import com.senior.d_text.presentation.core.OnItemClickListener

class DetectHistoryAdapter: RecyclerView.Adapter<DetectHistoryViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(layoutInflater, parent, false)
        return DetectHistoryViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: DetectHistoryViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }
}

class DetectHistoryViewHolder(
    val binding: ItemNotificationBinding,
    listener: OnItemClickListener
): RecyclerView.ViewHolder(binding.root) {

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