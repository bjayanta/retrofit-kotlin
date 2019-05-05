package xyz.jayanta.examsystem.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val AUTH: String = "Bearer " + Base64.encodeToString("jayanta:123456".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL: String = "http://192.168.0.244/prototype/api/"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", AUTH)
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}
