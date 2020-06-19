package com.app.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.system.exitProcess

class ForgotPassData : AppCompatActivity() {

    lateinit var tvPhone : TextView
    lateinit var tvEmail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_data)

        title = "Forgot Password Data"

        tvPhone = findViewById(R.id.tvPhone)
        tvEmail = findViewById(R.id.tvEmail)

        tvEmail.setText(intent.getStringExtra("Email"))
        tvPhone.setText(intent.getStringExtra("Phone"))
    }
}
