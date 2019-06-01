package com.joe.fly.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.joe.fly.R
import com.joe.fly.models.Destination
import com.joe.fly.services.DestinationService
import com.joe.fly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras
        if (bundle?.containsKey(ARG_ITEM_ID)!!) {
            val id = intent.getIntExtra(ARG_ITEM_ID, 0)
            loadDetails(id)
            updateDetails(id)
            deleteDestination(id)
        }
    }

    private fun loadDetails(id: Int) {
        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall = destinationService.getDestination(id)

        requestCall.enqueue(object : Callback<Destination> {
            override fun onFailure(call: Call<Destination>, t: Throwable) {
                Toast.makeText(this@DestinationDetailActivity, "Failed to retrieve details.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                if (response.isSuccessful) {
                    val destination = response.body()
                    destination?.let {
                        editText_city.setText(it.city)
                        editText_country.setText(it.country)
                        editText_description.setText(it.description)

                        collapsing_toolbar.title = it.city
                    }
                } else {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to retrieve details.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun updateDetails(id: Int) {
        update.setOnClickListener {
            val city = editText_city.text.toString()
            val country = editText_country.text.toString()
            val desc = editText_description.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.updateDestination(id, city, desc, country)

            requestCall.enqueue(object : Callback<Destination> {
                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to update item.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish()
                        response.body()
                        Toast.makeText(this@DestinationDetailActivity, "Item updated successfully.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to update item.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
        }
    }

    private fun deleteDestination(id: Int) {
        delete.setOnClickListener {
            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.deleteDestination(id)

            requestCall.enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to delete item.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        finish()
                        Toast.makeText(this@DestinationDetailActivity, "Item successfully deleted.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to delete item.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
