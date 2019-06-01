package com.joe.fly.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.joe.fly.R
import com.joe.fly.models.Destination
import com.joe.fly.services.DestinationService
import com.joe.fly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_create_destination.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateDestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_destination)

        setSupportActionBar(detail_toolbar)

        val context = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = editText_city.text.toString()
            newDestination.country = editText_country.text.toString()
            newDestination.description = editText_description.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.addDestination(newDestination)

            requestCall.enqueue(object : Callback<Destination> {
                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(context, "Failed to add item.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish()
                        response.body() // Use it or ignore it
                        Toast.makeText(context, "Item successfully added.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to add item.", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}
