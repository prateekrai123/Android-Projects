package com.app.foodie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Register : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var etEmail : EditText
    lateinit var etPhone : EditText
    lateinit var etDelAdd : EditText
    lateinit var etPass : EditText
    lateinit var etConfPass : EditText
    lateinit var btRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title = "Register"

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etMobile)
        etDelAdd = findViewById(R.id.etAddress)
        etPass = findViewById(R.id.etPassword)
        etConfPass = findViewById(R.id.etConfPassword)
        btRegister = findViewById(R.id.btRegister)

        btRegister.setOnClickListener{
            val intent  = Intent(this, RegisterData::class.java)
            intent.putExtra("name", etName.text.toString())
            intent.putExtra("email", etEmail.text.toString())
            intent.putExtra("phone", etPhone.text.toString())
            intent.putExtra("delAdd", etDelAdd.text.toString())
            intent.putExtra("pass", etPass.text.toString())
            intent.putExtra("confPass", etConfPass.text.toString())
            startActivity(intent)
        }

    }
}
