package com.example.faircorpapp.model

data class HeaterDto(
    val id: Long,
    val name: String,
    val power: Int,
    val status: HeaterStatus,
    val room_id: Long,
    val room_name: String
)
