package com.shadspace.spacenotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Using Handler to delay the intent for MainActivity
        Handler().postDelayed({
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish() // Closing splash activity
        }, SPLASH_TIME_OUT)
    }
}