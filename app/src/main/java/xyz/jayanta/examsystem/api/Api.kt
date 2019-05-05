package xyz.jayanta.examsystem.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.jayanta.examsystem.models.DefaultResponse

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun createUser(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("cpassword") cpassword: String
    ) :Call<DefaultResponse>


}

