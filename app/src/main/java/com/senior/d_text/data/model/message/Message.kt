package com.senior.d_text.data.model.message

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "message")
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("date_time")
    val date_time: String,
): Serializable