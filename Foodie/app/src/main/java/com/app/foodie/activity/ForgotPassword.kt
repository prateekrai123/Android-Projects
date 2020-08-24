package com.app.foodie.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class ForgotPassword : AppCompatActivity() {

    lateinit var btNext : Button
    lateinit var etPhone : EditText
    lateinit var etEmail : EditText
    lateinit var sharedPreferences: SharedPreferences
    lateinit var toolBar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        title = "Forgot Password"

        btNext = findViewById(R.id.btNext)
        etPhone = findViewById(R.id.etMobile)
        etEmail = findViewById(R.id.etEmail)
        sharedPreferences = getSharedPreferences("Foodie", Context.MODE_PRIVATE)
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Forgot Password"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPreferences.edit().putString("mobile_number", etMobile.text.toString()).apply()

        btNext.setOnClickListener{
            val queue = Volley.newRequestQueue(this)
            val url = "http://13.235.250.119/v2/forgot_password/fetch_result/"
            val jsonParams = JSONObject()
            jsonParams.put("mobile_number", etPhone.text.toString())
            jsonParams.put("email", etEmail.text.toString())
            val jsonObjectRequest  = object : JsonObjectRequest(Request.Method.POST, url, jsonParams,
                Response.Listener {
                    val data = it.getJSONObject("data")
                    val success = data.getBoolean("success")
                    if(success){
                        val intent = Intent(this, OtpPage::class.java)
                        intent.putExtra("mobile", etPhone.text.toString())
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Some error occurred $it", Toast.LENGTH_LONG).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(this, "Some error occurred $it", Toast.LENGTH_LONG).show()
                }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "9bf534118365f1"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)
        }

    }
}
