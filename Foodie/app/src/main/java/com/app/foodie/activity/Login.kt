package com.app.foodie.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import org.json.JSONObject

class Login : AppCompatActivity() {

    lateinit var btLogin : Button
    lateinit var etMobile : EditText
    lateinit var etPassword : EditText
    lateinit var btRegister : Button
    lateinit var forgotPassword : TextView
    lateinit var sharedPreferences : SharedPreferences
    lateinit var progressLayout : RelativeLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), Context.MODE_PRIVATE)

        val isLogged = sharedPreferences.getBoolean("isLogged", false)

        if(isLogged){
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        toolbar = findViewById(R.id.toolbar)
        btLogin = findViewById(R.id.btLogin)
        btRegister = findViewById(R.id.btRegister)
        etMobile = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        forgotPassword = findViewById(R.id.forgotPassword)
        progressLayout = findViewById(R.id.progressLayout)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Login"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btLogin.setOnClickListener {
            if (etMobile.text.length <= 9) {
                etMobile.error = "Invalid mobile number"
            } else if (etPassword.text.length < 6) {
                etPassword.error = "Must not be less than 6"
            } else {
                progressLayout.visibility = View.VISIBLE
                val queue = Volley.newRequestQueue(this)
                val url = "http://13.235.250.119/v2/login/fetch_result/"
                val jsonParams = JSONObject()
                jsonParams.put("mobile_number", etMobile.text.toString())
                jsonParams.put("password", etPassword.text.toString())
                val jsonObjectRequest = object :
                    JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {
                        val data = it.getJSONObject("data")
                        val success = data.getBoolean("success")
                        if (success) {
                            progressLayout.visibility = View.GONE
                            sharedPreferences.edit().putBoolean("isLogged", true).apply()
                            val data1 = data.getJSONObject("data")
                            sharedPreferences.edit().putString("user_id" ,data1.getString("user_id")).apply()
                            val intent = Intent(this, Main::class.java)
                            val bundle = Bundle()
                            bundle.putString("name", data1.getString("name"))
                            bundle.putString("email", data1.getString("email"))
                            bundle.putString("phone", data1.getString("mobile_number"))
                            bundle.putString("address", data1.getString("address"))
                            intent.putExtra("bundle", bundle)
                            startActivity(intent)
                            finish()
                        } else {
                            progressLayout.visibility = View.GONE
                            Toast.makeText(this, "Some error occurred $it", Toast.LENGTH_LONG)
                                .show()
                        }
                    }, Response.ErrorListener {
                        progressLayout.visibility = View.GONE
                        Toast.makeText(this, "Some error has occured $it", Toast.LENGTH_LONG).show()
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
        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }

        btRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}