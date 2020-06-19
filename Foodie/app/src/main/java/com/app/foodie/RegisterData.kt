package com.app.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterData : AppCompatActivity() {

    lateinit var tvName : TextView
    lateinit var tvEmail : TextView
    lateinit var tvPhone : TextView
    lateinit var tvDelAdd : TextView
    lateinit var tvPass : TextView
    lateinit var tvConfPass : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_data)

        title = "Register Data"

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhone = findViewById(R.id.tvPhone)
        tvDelAdd = findViewById(R.id.tvDelAdd)
        tvPass = findViewById(R.id.tvPass)
        tvConfPass = findViewById(R.id.tvConfPass)

        tvName.setText(intent.getStringExtra("name"))
        tvEmail.setText(intent.getStringExtra("email"))
        tvPhone.setText(intent.getStringExtra("phone"))
        tvDelAdd.setText(intent.getStringExtra("delAdd"))
        tvPass.setText(intent.getStringExtra("password"))
        tvConfPass.setText(intent.getStringExtra("confPass"))

    }
}
