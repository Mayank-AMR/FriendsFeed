package com.example.friendsfeed.auth.data.network

import com.example.friendsfeed.auth.data.network.responses.AuthResponse
import com.example.friendsfeed.auth.data.network.responses.RegistrationResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

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

    @FormUrlEncoded
    @POST("register")
    suspend fun userSignup(
            @Header("Accept") accept_type: String?,
            @Field("name") name: String,
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("gender") gender: String,
            @Field("dob") dob: String
    ): Response<RegistrationResponse>


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