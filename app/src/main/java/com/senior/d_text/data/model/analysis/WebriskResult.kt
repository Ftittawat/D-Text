package com.senior.d_text.data.model.analysis


import com.google.gson.annotations.SerializedName

data class WebriskResult(
    @SerializedName("url_threat_type")
    val urlThreatType: String,
    @SerializedName("url_type")
    val urlType: String
)