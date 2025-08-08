package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ActivityLogin: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var  email: EditText

    private lateinit var password: EditText

    private lateinit var loginButton: Button

    private lateinit var forgotPasswordButton: TextView

    private lateinit var signupButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.emaillogin)
        password = findViewById(R.id.passwordlogin)
        loginButton = findViewById(R.id.btnlogin)
        signupButton = findViewById(R.id.gotosignup)
        forgotPasswordButton = findViewById(R.id.forgotpassword)

        signupButton.setOnClickListener {
            val intent = Intent(this, ActivitySignup::class.java)
            startActivity(intent)
            finish() // Close the login activity
        }

        forgotPasswordButton.setOnClickListener {
            val intent = Intent(this, ActivityForgotPassword::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if(emailText.isNotEmpty() && passwordText.isNotEmpty())
            {
                loginUser(emailText, passwordText)
            }
            else
            {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Close the login activity
                } else {
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


}