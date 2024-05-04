package com.senior.d_text.data.model.history

import com.google.gson.annotations.SerializedName

data class HistoryList(
    @SerializedName("result")
    val history: List<History>
)
