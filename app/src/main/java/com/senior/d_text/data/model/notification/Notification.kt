package com.senior.d_text.data.model.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "notification")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("url")
    val url: String,
    @SerializedName("risk_level")
    val risk_level: String,
    @SerializedName("application")
    val application: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("date_time")
    val date_time: String
): Serializable
