package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the action bar
        supportActionBar?.hide()

        // Using Handler to delay the transition to LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start LoginActivity
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)

            // Close this activity
            finish()
        }, splashTimeOut)
    }
}
