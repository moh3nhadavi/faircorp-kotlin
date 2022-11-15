package com.example.faircorpapp.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiServices {
//    const val baseUrl = "https://app-611f79a5-3432-4a1e-b16b-7fdffa43658f.cleverapps.io/api/"
    const val baseUrl = "https://mohsen-hadavi.cleverapps.io/"
    val roomApiService : RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(RoomApiService::class.java)
    }
    val buildingApiService : BuildingApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(BuildingApiService::class.java)
    }
}