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
    @SerializedName("d_text_level")
    val d_text_level: String,
    @SerializedName("google_level")
    val google_level: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("date_time")
    val date_time: String,
    @SerializedName("domain_age_day")
    val domainAgeDay: Int,
    @SerializedName("has_form")
    val hasForm: Boolean,
    @SerializedName("has_iframe")
    val hasIframe: Boolean,
    @SerializedName("has_redirect_url")
    val hasRedirectUrl: String,
    @SerializedName("has_shortened")
    val hasShortened: Boolean,
    @SerializedName("has_ssl")
    val hasSsl: Boolean,
    @SerializedName("url_score")
    val urlScore: Double
): Serializable
