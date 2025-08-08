package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ActivitySignup: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var  email: EditText

    private lateinit var password: EditText

    private lateinit var signupButton: Button

    private lateinit var backButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.emailsignup)
        password = findViewById(R.id.passwordsignup)
        signupButton = findViewById(R.id.btnsignup)
        backButton = findViewById(R.id.backtologin)

        backButton.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish() // Close the signup activity
        }

        signupButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if(emailText.isNotEmpty() && passwordText.isNotEmpty())
            {
                signupUser(emailText, passwordText)
            }
            else
            {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signupUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "signup successful,now please login with your details", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ActivityLogin::class.java))
                    finish() // Close the login activity
                } else {
                    Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


}