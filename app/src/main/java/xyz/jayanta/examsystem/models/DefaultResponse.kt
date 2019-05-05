package xyz.jayanta.examsystem.models

import com.google.gson.annotations.SerializedName

data class DefaultResponse (
    @SerializedName("success") var success: ArrayList<SuccessResponse>,
    val error: Boolean
)

data class SuccessResponse(
    val token: String,
    val name: String
)