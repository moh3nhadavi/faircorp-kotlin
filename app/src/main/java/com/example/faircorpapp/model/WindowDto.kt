package com.example.faircorpapp.model

data class WindowDto(
    val id: Long,
    val name: String,
    val status: WindowStatus,
    val room_id: Long,
    val room_name: String
)
