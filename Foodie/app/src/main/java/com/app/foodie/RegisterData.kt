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
    lateinit var btExit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_data)

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhone = findViewById(R.id.tvPhone)
        tvDelAdd = findViewById(R.id.tvDelAdd)
        tvPass = findViewById(R.id.tvPass)
        tvConfPass = findViewById(R.id.tvConfPass)
        btExit = findViewById(R.id.btExit)

        tvName.setText("Name :- "+intent.getStringExtra("name"))
        tvEmail.setText("Email :- "+intent.getStringExtra("email"))
        tvPhone.setText("Phone :- "+intent.getStringExtra("phone"))
        tvDelAdd.setText("Delievery Address :- \n"+intent.getStringExtra("delAdd"))
        tvPass.setText("Password :- "+intent.getStringExtra("password"))
        tvConfPass.setText("Confirm Password :- \n"+intent.getStringExtra("confPass"))

        btExit.setOnClickListener{
            onDestroy()
            finish()
        }
    }
}
