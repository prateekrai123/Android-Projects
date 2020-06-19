package com.app.foodie

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Login : AppCompatActivity() {

    lateinit var btLogin : Button
    lateinit var etMobile : EditText
    lateinit var etPassword : EditText
    lateinit var btRegister : Button
    lateinit var forgotPassword : TextView
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), Context.MODE_PRIVATE)

        var isLogged = sharedPreferences.getBoolean("isLogged", false)

        if(isLogged){
            val intent = Intent(this, LoginData::class.java)
            startActivity(intent)
        }

        btLogin = findViewById(R.id.btLogin)
        btRegister = findViewById(R.id.btRegister)
        etMobile = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        forgotPassword = findViewById(R.id.forgotPassword)

        btLogin.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLogged", true).apply()
            val intent = Intent(this, LoginData::class.java)
//            intent.putExtra("phone", etMobile.text.toString())
//            intent.putExtra("password", etPassword.text.toString())
            sharedPreferences.edit().putString("phone", etMobile.text.toString()).apply()
            sharedPreferences.edit().putString("password", etPassword.text.toString()).apply()
            startActivity(intent)
        }

        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }

        btRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }
}