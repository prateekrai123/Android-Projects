package com.app.foodie.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.app.foodie.R
import com.app.foodie.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.nav_header.*

class Main : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var navigationView : NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_drawer)

        val bundle : Bundle? = intent.getBundleExtra("bundle")

        navigationView = findViewById(R.id.navigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        frameLayout = findViewById(R.id.frame)
        toolbar = findViewById(R.id.toolbar)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@Main, drawerLayout, R.string.open_drawer, R.string.close_drawer)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "All Restaurants"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, Home())
            .commit()
        navigationView.setCheckedItem(R.id.home)

        sharedPreferences = getSharedPreferences("Foodie", Context.MODE_PRIVATE)

        navigationView.setNavigationItemSelectedListener {
            it.isCheckable = true
            it.isChecked = true

            when(it.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, Home())
                        .commit()
                    navigationView.setCheckedItem(R.id.home)

                    drawerLayout.closeDrawers()
                }
                R.id.Favourite_restaurants -> {
                    val favourites = FavouriteRestaurants()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, favourites)
                        .commit()
                    navigationView.setCheckedItem(R.id.Favourite_restaurants)

                    drawerLayout.closeDrawers()
                }
                R.id.my_profile -> {
                    val profile = MyProfile()
                    profile.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, profile)
                        .commit()
                    navigationView.setCheckedItem(R.id.my_profile)

                    drawerLayout.closeDrawers()
                }
                R.id.faqs -> {
                    val faqs = Faqs()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, faqs)
                        .commit()
                    navigationView.setCheckedItem(R.id.faqs)
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    AlertDialog.Builder(this).setMessage("Do you want to log out?")
                        .setTitle("Logout")
                        .setPositiveButton("Yes"){
                            dialog, which ->
                            run {
                                sharedPreferences.edit().putBoolean("isLogged", false).apply()
                                startActivity(Intent(this, Login::class.java))
                                finish()
                            }
                        }
                        .setNegativeButton("No"){
                            dialog, which ->  run{

                        }
                        }
                }
                R.id.order_history -> {
                    val ordHis = OrderHistory()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ordHis)
                        .commit()
                    navigationView.setCheckedItem(R.id.order_history)
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}