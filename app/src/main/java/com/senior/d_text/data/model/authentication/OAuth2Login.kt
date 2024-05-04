package com.senior.d_text.data.model.authentication


import com.google.gson.annotations.SerializedName

data class OAuth2Login(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)