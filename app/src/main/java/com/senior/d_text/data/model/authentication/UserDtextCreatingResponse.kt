package com.senior.d_text.data.model.authentication


import com.google.gson.annotations.SerializedName

data class UserDtextCreatingResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("username")
    val username: String
)