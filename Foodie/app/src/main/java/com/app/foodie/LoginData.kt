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
    lateinit var btLogOut : Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_data)

        title = "Login Data"

        sharedPreferences = getSharedPreferences("Foodie", Context.MODE_PRIVATE)

        tvPhone = findViewById(R.id.tvPhone)
        tvPassword = findViewById(R.id.tvPassword)
        btLogOut = findViewById(R.id.btLogOut)
        btLogOut.setOnClickListener{
            val intent  = Intent(this, Login::class.java)
            sharedPreferences.edit().putBoolean("isLogged", false).apply()
            startActivity(intent)
        }

        val phone = sharedPreferences.getString("phone", "")
        val password = sharedPreferences.getString("password", "")

        tvPhone.text = phone
        tvPassword.text = password

    }
}
