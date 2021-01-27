package com.example.plant.data.loginpojo

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val currentUser: CurrentUser
)