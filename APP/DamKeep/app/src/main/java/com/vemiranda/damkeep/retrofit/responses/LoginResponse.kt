package com.vemiranda.damkeep.retrofit.responses

import com.santiagorodriguezalberto.damkeepapp.model.User

data class LoginResponse(
    val token: String,
    val user: User
)