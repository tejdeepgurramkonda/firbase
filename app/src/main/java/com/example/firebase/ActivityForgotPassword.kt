package com.example.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

// Remove this import if you are not using it elsewhere, it's not needed for this fix.
// import kotlin.jvm.Throws

class ActivityForgotPassword : AppCompatActivity() {
    // Declare FirebaseAuth instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)
        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        lateinit var email: EditText
        lateinit var resetButton: Button
        lateinit var backButton: TextView

        email = findViewById(R.id.emailforgot)
        resetButton = findViewById(R.id.btnforgot)
        backButton = findViewById(R.id.backtologin)

        resetButton.setOnClickListener {
            val emailText = email.text.toString()
            if (emailText.isNotEmpty()) {
                // Call sendPasswordResetEmail on the auth instance
                auth.sendPasswordResetEmail(emailText)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Password reset email sent.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish() // Close the forgot password activity and return to the login screen
        }
    }
}
