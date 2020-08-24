package com.app.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.foodie.R

class OrderPlacedActivity : AppCompatActivity() {

    lateinit var btnOk : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed)

        btnOk = findViewById(R.id.btnOk)

        btnOk.setOnClickListener {
            startActivity(Intent(this, Main::class.java))
            finishAffinity()
        }
    }
}