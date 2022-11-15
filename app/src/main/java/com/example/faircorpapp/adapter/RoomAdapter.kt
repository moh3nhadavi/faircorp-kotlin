package com.example.faircorpapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorpapp.R
import com.example.faircorpapp.model.RoomDto

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_room_item_name)
        val floor: TextView = view.findViewById(R.id.txt_room_item_floor)
        val currentTemp: TextView = view.findViewById(R.id.txt_room_item_current_temp)
        val targetTemp: TextView = view.findViewById(R.id.txt_room_item_target_temp)
    }

    private val items = mutableListOf<RoomDto>() // (3)

//    @SuppressLint("NotifyDataSetChanged")
    fun update(rooms: List<RoomDto>) {  // (4)
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_room_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {  // (7)
        val room = items[position]
        holder.apply {
            name.text = room.name
            floor.text = room.floor.toString()
            currentTemp.text = room.current_temperature.toString()
            targetTemp.text = room.target_temperature.toString()
        }
    }
}