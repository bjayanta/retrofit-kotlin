package xyz.jayanta.examsystem.models

data class DefaultResponse (
    val success: String,
    val error: String
)

data class SuccessList(
    val token: String,
    val name: String
)