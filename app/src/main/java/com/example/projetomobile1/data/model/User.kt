package com.example.projetomobile1.data.model

data class UserRequest(
    val email: String,
    val senha: String
)

data class LoginResponse(
    val token: String,
    val message: String
)


