package com.senior.d_text.data.model.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("url")
    val url: String,
    @SerializedName("risk_level")
    val risk_level: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date_time")
    val date_time: String
): Serializable
