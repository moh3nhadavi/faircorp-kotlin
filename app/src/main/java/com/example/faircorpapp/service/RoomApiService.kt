package com.example.faircorpapp.service

import com.example.faircorpapp.model.RoomDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface RoomApiService {
    @GET("/api/buildings/{building_username}/rooms")
    fun findAll(@Path("building_username") building_username: String): Call<List<RoomDto>>

    @Headers("Content-Type: application/json")
    @POST("/api/buildings/{building_username}/rooms")
    fun create(@Path("building_username") building_username: String): Call<RoomDto>

    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>
}