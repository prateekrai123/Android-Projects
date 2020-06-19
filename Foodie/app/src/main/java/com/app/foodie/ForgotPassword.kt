package com.app.foodie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ForgotPassword : AppCompatActivity() {

    lateinit var btNext : Button
    lateinit var etPhone : EditText
    lateinit var etEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        title = "Forgot Password"

        btNext = findViewById(R.id.btNext)
        etPhone = findViewById(R.id.etMobile)
        etEmail = findViewById(R.id.etEmail)

        btNext.setOnClickListener{
            val intent  = Intent(this, ForgotPassData::class.java)
            intent.putExtra("Phone", etPhone.text.toString())
            intent.putExtra("Email", etEmail.text.toString())
            startActivity(intent)
        }

    }
}
