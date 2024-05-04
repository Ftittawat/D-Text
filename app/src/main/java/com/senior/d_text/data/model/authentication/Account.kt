package com.senior.d_text.data.model.authentication


import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("UserDtextCreatingResponse")
    val userDtextCreatingResponse: UserDtextCreatingResponse,
    @SerializedName("UserTokenResponse")
    val userTokenResponse: UserTokenResponse,
    @SerializedName("error")
    val error: String
)