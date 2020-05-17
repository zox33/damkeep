package com.santiagorodriguezalberto.damkeepapp.model

import java.util.*

data class User(
    val email: String,
    val fullName: String,
    val id: String,
    val roles: String,
    val notas: List<Any>,
    val username: String
)