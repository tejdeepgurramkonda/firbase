package firebasecrudoperations

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.R // Make sure this R import is correct for your project structure

import com.google.firebase.database.DatabaseError // Needed for OnFailureListener type
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
// You can also use 'import com.google.firebase.ktx.Firebase' and then 'Firebase.database.getReference(...)'

data class User(
    val id: String? = null, // Firebase keys are usually strings
    val name: String? = null,
    val email: String? = null
)

class MainActivity : AppCompatActivity() { // Renamed from User.kt if this is your main activity
    private lateinit var etUserId: EditText
    private lateinit var etUserName: EditText
    private lateinit var etUserEmail: EditText
    private lateinit var btnCreate: Button
    // private lateinit var btnRead: Button // Assuming you'll add these later
    // private lateinit var btnUpdate: Button
    // private lateinit var btnDelete: Button

    private lateinit var database: DatabaseReference // This should now be resolved

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure 'activity_main_database' is the correct layout file name
        // and that it exists in res/layout
        setContentView(R.layout.activity_main_database)

        etUserId = findViewById(R.id.etUserId)
        etUserName = findViewById(R.id.etUserName)
        etUserEmail = findViewById(R.id.etUserEmail)
        btnCreate = findViewById(R.id.btncreate) // Ensure R.id.btncreate matches your XML
        // btnRead = findViewById(R.id.btnRead)
        // btnUpdate = findViewById(R.id.btnUpdate)
        // btnDelete = findViewById(R.id.btnDelete)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().getReference("users")
        // Alternative using KTX: database = Firebase.database.getReference("users")

        btnCreate.setOnClickListener {
            val userId = etUserId.text.toString().trim() // Use trim()
            val userName = etUserName.text.toString().trim()
            val userEmail = etUserEmail.text.toString().trim()

            if (userId.isNotEmpty() && userName.isNotEmpty() && userEmail.isNotEmpty()) {
                val user = User(userId, userName, userEmail)

                database.child(userId).setValue(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                        // Clear fields after successful creation
                        etUserId.text.clear()
                        etUserName.text.clear()
                        etUserEmail.text.clear()
                    }
                    .addOnFailureListener { exception -> // Specify type for lambda parameter
                        // The 'exception' parameter here is of type Exception (or more specific DatabaseError)
                        // 'message' is a property of Exception
                        Toast.makeText(
                            this,
                            "Failed to create user: ${exception.message}", // 'message' is now resolved
                            Toast.LENGTH_LONG
                        ).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
