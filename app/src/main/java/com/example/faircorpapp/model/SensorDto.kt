package com.example.faircorpapp.model

data class SensorDto(
    val id: Long,
    val name: String,
    val temperature: Double,
    val room_id: Long
)
