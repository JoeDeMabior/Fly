package com.joe.fly.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joe.fly.R

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
