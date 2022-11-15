package com.example.faircorpapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.faircorpapp.R
import com.example.faircorpapp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_create)
    }

    fun submitRoom(view: View){
        val building_username = intent.getStringExtra("buildingUsername")
        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching {
                ApiServices.roomApiService.create(building_username.toString()).execute()
            } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        Log.i("json file", it.body().toString())
                        val building = it.body()
                        Log.i("building",building?.name.toString())
//                        intent.putExtra("outside_temperature", building?.out_temperature.toString())
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
    }
}