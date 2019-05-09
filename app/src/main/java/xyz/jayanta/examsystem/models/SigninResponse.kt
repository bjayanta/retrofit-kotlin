package xyz.jayanta.examsystem.models

data class SigninResponse (
    val success: Boolean,
    val error: Boolean,
    val token: String,
    val user: User
)