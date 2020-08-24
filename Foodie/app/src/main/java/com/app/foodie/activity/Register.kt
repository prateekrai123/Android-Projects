package com.app.foodie.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import org.json.JSONObject

class Register : AppCompatActivity() {

    lateinit var etName : EditText
    lateinit var etEmail : EditText
    lateinit var etPhone : EditText
    lateinit var etDelAdd : EditText
    lateinit var etPass : EditText
    lateinit var etConfPass : EditText
    lateinit var btRegister: Button
    lateinit var progressLayout :RelativeLayout
    lateinit var toolbar: Toolbar

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
        progressLayout = findViewById(R.id.progressLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Register"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btRegister.setOnClickListener{
            if(etName.text.length==0){
                etName.error = "This field should not be blank"
            }
            else if(etEmail.text.length<6){
                etPhone.error = "Enter valid Email"
            }
            else if(etPhone.text.length<10){
                etPhone.error = "Enter valid phone number"
            }
            else if(etDelAdd.text.length==0){
                etDelAdd.error = "This field should not be blank"
            }
            else if(etPass.text.length==0){
                etPass.error = "This field should not be blank"
            }
            else if(etPass.text.length<6){
                etPass.error = "Not Strong"
            }
            else if(etPass.text.toString()!=etConfPass.text.toString()){
                etConfPass.error = "Password did not match"
            }
            else{
                progressLayout.visibility = View.VISIBLE
                val queue = Volley.newRequestQueue(this)
                val url = "http://13.235.250.119/v2/register/fetch_result/"
                val jsonParams = JSONObject()
                jsonParams.put("name", etName.text.toString())
                jsonParams.put("mobile_number", etPhone.text.toString())
                jsonParams.put("password", etPass.text.toString())
                jsonParams.put("address", etDelAdd.text.toString())
                jsonParams.put("email", etEmail.text.toString())
                val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url ,jsonParams, Response.Listener {
                    val data = it.getJSONObject("data")
                    val success = data.getBoolean("success")
                    if(success){
                        progressLayout.visibility = View.GONE
                        startActivity(Intent(this, Main::class.java))
                    }
                    else{
                        if(data.getString("errorMessage") =="Mobile number OR Email Id is already registered"){
                            progressLayout.visibility = View.GONE
                            Toast.makeText(this, data.getString("errorMessage"), Toast.LENGTH_SHORT).show()
                        }
                        progressLayout.visibility = View.GONE
                        Toast.makeText(this, "Some error occurred ${it.getJSONObject("data")}", Toast.LENGTH_LONG).show()
                    }
                },Response.ErrorListener {
                    progressLayout.visibility = View.GONE
                    Toast.makeText(this, "Some error has occurred $it", Toast.LENGTH_SHORT).show()
                }) {
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
}
