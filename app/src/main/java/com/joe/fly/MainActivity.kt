package com.joe.fly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.joe.fly.adapters.DestinationAdapter
import com.joe.fly.models.Destination
import com.joe.fly.services.DestinationService
import com.joe.fly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    private fun loadDestinations() {
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val filter = HashMap<String, String>()
        val requestCall = destinationService.getDestinationList(filter)
        requestCall.enqueue(object : Callback<List<Destination>> {
            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error occurred $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
                when {
                    response.isSuccessful -> {
                        val destinationList = response.body()!!
                        recycler_view.adapter = DestinationAdapter(destinationList)
                    }
                    response.code() == 401 -> Toast.makeText(
                        this@MainActivity,
                        "Session expired. Please login again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(this@MainActivity, "Failed to retrieve data.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        loadDestinations()
    }
}
