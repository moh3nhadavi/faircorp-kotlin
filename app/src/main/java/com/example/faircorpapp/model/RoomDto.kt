package com.example.faircorpapp.model

data class RoomDto(
    val id: Long?,
//    val building_username: String?,
    val name: String,
    val floor: Int,
    val current_temperature: Double?,
    val target_temperature: Double?
)
