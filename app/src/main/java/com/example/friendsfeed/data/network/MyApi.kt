package com.example.friendsfeed.data.network

import com.example.friendsfeed.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * @Project Sample Chat
 * @Created_by Mayank Kumar on 08-09-2020 08:26 AM
 */
interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
            @Header("Accept") accept_type: String?,
            @Field("email") email: String,
            @Field("password") password: String
    ): Response<AuthResponse>

    companion object {
        operator fun invoke(
                networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(networkConnectionInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://friendsfeed.herokuapp.com/api/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)
        }
    }
}