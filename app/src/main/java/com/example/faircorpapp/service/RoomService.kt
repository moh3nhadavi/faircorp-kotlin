package com.example.faircorpapp.service

import com.example.faircorpapp.model.RoomDto

class RoomService {
    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room EF 6.10",1, 18.2, 20.0),
            RoomDto(2, "Hall",1, 18.2, 18.0),
            RoomDto(3, "Room EF 7.10",2, 21.2, 20.0)
        )

    }

//    fun RoomService(){}

    fun findById(id: Long) = ROOMS.firstOrNull { it.id == id}

    fun findAll() = ROOMS.sortedBy { it.floor }

}