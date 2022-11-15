package com.example.faircorpapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.faircorpapp.R
import com.example.faircorpapp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openRoom(view: View) {

        // Extract value filled in editText identified with txt_window_name id
        val buildingName = findViewById<EditText>(R.id.txt_room_name).text.toString()
        // Display a message
        val intent = Intent(this, RoomActivity::class.java).apply {
            putExtra("buildingUsername", buildingName)
        }
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching {
                ApiServices.buildingApiService.findByUsername(buildingName).execute()
            } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        Log.i("json file", it.body().toString())
                        val building = it.body()
                        Log.i("building",building?.name.toString())
                        intent.putExtra("outside_temperature", building?.out_temperature.toString())
                        intent.putExtra("hotel_name", building?.name.toString())
                        startActivity(intent)
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Log.i("fail", "$it")
                        Toast.makeText(
                            applicationContext,
                            "Error on Building loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

//        Log.i("test", roomName)
//        Toast.makeText(this, "You choose $roomName", Toast.LENGTH_LONG).show()
//        val intent = Intent(this, RoomActivity::class.java).apply {
//            putExtra("roomName", roomName)
//        }
//        startActivity(intent)
    }
}