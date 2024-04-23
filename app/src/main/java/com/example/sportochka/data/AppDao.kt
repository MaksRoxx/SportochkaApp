package com.example.sportochka.data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AppDao {
    @POST("user/register")
    fun register(@Header("Authorization") user: String)

    @POST("user/login")
    fun login(@Header("Authorization") user: String): Call<String>

    @GET("profile")
    suspend fun getProfile(): AllOutProfile

    @POST("profile/add_friend")
    suspend fun addFriend(@Query("q") name: String)

    @POST("profile/update_coords")
    suspend fun updateCoordinats(@Body coords: JsonObject)

    @POST("profile/update")
    suspend fun updateMyCoordinats(@Body profile: JsonObject)

    companion object {
        const val BASE_URL = "http://192.168.234.93:8082"
    }
}