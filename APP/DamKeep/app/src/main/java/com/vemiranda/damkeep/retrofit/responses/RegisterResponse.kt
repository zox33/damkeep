package com.vemiranda.damkeep.retrofit.responses

data class RegisterResponse(
    val email: String,
    val fullName: String,
    val id: String,
    val roles: String,
    val username: String
)