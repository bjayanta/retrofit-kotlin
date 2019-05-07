package xyz.jayanta.examsystem.models

data class SignupResponse (
    val msg: String,
    val success: Boolean,
    val error: Boolean,
    val token: String,
    val name: String
)