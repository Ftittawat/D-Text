package com.senior.d_text.data.model.authentication

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
    val phone_number: String
)
