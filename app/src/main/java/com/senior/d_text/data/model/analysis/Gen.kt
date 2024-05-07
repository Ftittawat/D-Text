package com.senior.d_text.data.model.analysis


import com.google.gson.annotations.SerializedName

data class Gen(
    @SerializedName("dtext_result")
    val dtextResult: DtextResult,
    @SerializedName("webrisk_result")
    val webriskResult: WebriskResult
)