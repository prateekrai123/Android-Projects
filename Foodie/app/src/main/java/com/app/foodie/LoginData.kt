package com.app.foodie

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginData : AppCompatActivity() {

    lateinit var tvPhone : TextView
    lateinit var tvPassword : TextView
    lateinit var btExit : Button
    lateinit var btLogOut : Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_data)

        sharedPreferences = getSharedPreferences("Foodie", Context.MODE_PRIVATE)

        tvPhone = findViewById(R.id.tvPhone)
        tvPassword = findViewById(R.id.tvPassword)
        btExit = findViewById(R.id.btExit)
        btLogOut = findViewById(R.id.btLogOut)
        btLogOut.setOnClickListener{
            val intent  = Intent(this, Login::class.java)
            sharedPreferences.edit().putBoolean("isLogged", false).apply()
            startActivity(intent)
        }

        btExit.setOnClickListener{
            finish()
        }
        val phone = intent.getStringExtra("phone")
        val password = intent.getStringExtra("password")

        tvPhone.text = "Phone :- "+phone
        tvPassword.text = "Password :- "+password

    }
}
