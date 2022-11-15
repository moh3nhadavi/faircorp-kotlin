package com.example.faircorpapp.service

import com.example.faircorpapp.model.BuildingDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuildingApiService {

    @GET("/api/buildings/{username}")
    fun findByUsername(@Path("username") username: String): Call<BuildingDto>
}