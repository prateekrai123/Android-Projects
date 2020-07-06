package com.app.foodie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.app.foodie.R

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
