package com.senior.d_text.data.model.analysis


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("domain_age_days")
    val domainAgeDays: Int,
    @SerializedName("has_form")
    val hasForm: Boolean,
    @SerializedName("has_iframe")
    val hasIframe: Boolean,
    @SerializedName("shortened")
    val shortened: Boolean,
    @SerializedName("ssl")
    val ssl: Boolean
)