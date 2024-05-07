package com.senior.d_text.data.model.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "receive_notification")
data class ReceiveNotification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("appName")
    val appName: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("dateTime")
    var dateTime: String
): Serializable
