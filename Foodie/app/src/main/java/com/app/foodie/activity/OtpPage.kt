package com.app.foodie.activity

import android.content.Context
import android.content.Intent
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

class OtpPage : AppCompatActivity() {

    lateinit var etOtp : EditText
    lateinit var etNewPass : EditText
    lateinit var etConfPass : EditText
    lateinit var btSubmit : Button
    lateinit var progressLayout : RelativeLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_page)

        etOtp = findViewById(R.id.etOtp)
        etNewPass = findViewById(R.id.etNewPass)
        etConfPass = findViewById(R.id.etConfPass)
        btSubmit = findViewById(R.id.btSubmit)
        progressLayout = findViewById(R.id.progressLayout)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Change Password"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btSubmit.setOnClickListener{
            if(etOtp.text.length<4){
                etOtp.error = "Invalid OTP"
            }
            else if(etNewPass.text.length<6){
                etNewPass.error = "Password should not be les than 6 letters"
            }
            else if(etNewPass.text.toString().trim() != etConfPass.text.toString().trim()){
                etConfPass.error = "Does not match"
            }
            else{
                progressLayout.visibility = View.VISIBLE
                val queue = Volley.newRequestQueue(this)
                val url = "http://13.235.250.119/v2/reset_password/fetch_result/"
                val jsonParams = JSONObject()
                jsonParams.put("mobile_number", intent.getStringExtra("mobile"))
                jsonParams.put("password", etNewPass.text.toString())
                jsonParams.put("otp", etOtp.text.toString())
                val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url, jsonParams,
                    Response.Listener {
                        val data = it.getJSONObject("data")
                        val success = data.getBoolean("success")
                        if(success){
                            progressLayout.visibility = View.GONE
                            Toast.makeText(this, data.getString("successMessage"), Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, Login::class.java))
                        }
                        else{
                            progressLayout.visibility = View.GONE
                            Toast.makeText(this, "Some error occurred $it", Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener {
                        progressLayout.visibility = View.GONE
                        Toast.makeText(this, "Some error has occurred $it", Toast.LENGTH_LONG).show()
                    }){
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["content-type"] = "application/json"
                        headers["token"] = "9bf534118365f1"
                        return headers
                    }
                }
                queue.add(jsonObjectRequest)
            }
        }
    }
}