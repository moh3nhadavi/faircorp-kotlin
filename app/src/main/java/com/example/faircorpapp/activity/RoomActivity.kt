package com.example.faircorpapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.faircorpapp.R
import com.example.faircorpapp.adapter.RoomAdapter
import com.example.faircorpapp.service.ApiServices
import com.example.faircorpapp.service.RoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {

    private val roomService = RoomService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val building_username = intent.getStringExtra("buildingUsername")
        val hotel_name = intent.getStringExtra("hotel_name")
        val outside_temperature = intent.getStringExtra("outside_temperature")
        val buildingUsername = findViewById<TextView>(R.id.txt_room_entered)
        buildingUsername.text = "Welcome to ${hotel_name}.\nOutside temperature is ${outside_temperature}"

        val recyclerView = findViewById<RecyclerView>(R.id.rv_room)
        val adapter = RoomAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices.roomApiService.findAll(building_username.toString()).execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        Log.i("json file",it.body().toString())
                        adapter.update(it.body() ?: emptyList())
                        recyclerView.adapter = adapter
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Log.i("fail", "$it")
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

    fun openRoomCreate(view: View) {
        val building_username = intent.getStringExtra("buildingUsername")
        val intent = Intent(this, RoomCreateActivity::class.java).apply {
            putExtra("buildingUsername", building_username)
        }
        startActivity(intent)
    }
}