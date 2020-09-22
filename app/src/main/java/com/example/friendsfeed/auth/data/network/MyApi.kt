package com.example.friendsfeed.auth.data.network

import com.example.friendsfeed.auth.data.network.responses.AuthResponse
import com.example.friendsfeed.auth.data.network.responses.RegistrationResponse
import com.example.friendsfeed.auth.data.network.responses.HomePostResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * @Project Sample Chat
 * @Created_by Mayank Kumar on 08-09-2020 08:26 AM
 */
interface MyApi {


    // This API call fun is for User login and return response in AuthResponse object type
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
            @Header("Accept") accept_type: String?,
            @Field("email") email: String,
            @Field("password") password: String
    ): Response<AuthResponse>


    // This API call fun is for new User Registration return response in RegistrationResponse object type
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


    // This API call fun return All Home Post in HomePostResponse object type
    @GET("get")
    suspend fun getHomePost(
            @Header("Accept") accept_type: String?,
            @Header("Authorization") authorization: String?
    ): Response<HomePostResponse>


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