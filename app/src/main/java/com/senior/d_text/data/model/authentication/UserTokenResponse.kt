package com.senior.d_text.data.model.authentication


import com.google.gson.annotations.SerializedName

data class UserTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)