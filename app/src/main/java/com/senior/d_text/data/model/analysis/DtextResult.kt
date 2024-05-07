package com.senior.d_text.data.model.analysis


import com.google.gson.annotations.SerializedName

data class DtextResult(
    @SerializedName("domain_age_day")
    val domainAgeDay: Int,
    @SerializedName("has_form")
    val hasForm: Boolean,
    @SerializedName("has_iframe")
    val hasIframe: Boolean,
    @SerializedName("has_shortened")
    val hasShortened: Boolean,
    @SerializedName("has_ssl")
    val hasSsl: Boolean,
    @SerializedName("organz_name")
    val organzName: String,
    @SerializedName("registrar_name")
    val registrarName: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("url_score")
    val urlScore: Double,
    @SerializedName("url_type")
    val urlType: String
)