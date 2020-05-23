package com.mvvm.csquare.data.source.remote

import com.google.gson.JsonObject
import com.mvvm.csquare.data.source.model.LoginResponse
import com.mvvm.csquare.data.source.model.RealestateResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCall {

    @POST("api/login")
    suspend fun loginUser(@Body jsonObject: HashMap<String, String>): Response<LoginResponse>

    @GET("api/users?page=2")
    suspend fun getUserData(): Response<RealestateResponse>

    companion object {
        operator fun invoke(): ApiCall {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://reqres.in/")
                .build()
                .create(ApiCall::class.java)
        }
    }
}