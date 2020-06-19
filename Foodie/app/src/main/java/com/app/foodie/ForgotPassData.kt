package com.app.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ForgotPassData : AppCompatActivity() {

    lateinit var tvPhone : TextView
    lateinit var tvEmail : TextView
    lateinit var btExit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_data)

        tvPhone = findViewById(R.id.tvPhone)
        tvEmail = findViewById(R.id.tvEmail)
        btExit = findViewById(R.id.btExit)

        tvEmail.setText("Email :- ${intent.getStringExtra("Email")}")
        tvPhone.setText("Phone :- ${intent.getStringExtra("Phone")}")

        btExit.setOnClickListener{
            onDestroy()
            finish()
        }
    }
}
