package xyz.jayanta.examsystem.models

data class SignupResponse (
    val message: String,
    val success: Boolean,
    val error: Boolean,
    val token: String,
    val name: String
)