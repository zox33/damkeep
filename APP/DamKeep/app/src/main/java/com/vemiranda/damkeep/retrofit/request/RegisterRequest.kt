package com.vemiranda.damkeep.retrofit.request

data class RegisterRequest(
    val email: String,
    val fullName: String,
    val password: String,
    val password2: String,
    val username: String
)