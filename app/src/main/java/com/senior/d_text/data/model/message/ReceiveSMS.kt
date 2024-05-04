package com.senior.d_text.data.model.message

data class ReceiveSMS(
    var sender: String,
    var messageBody: String,
    var url: String,
    var dateTime: String
)
