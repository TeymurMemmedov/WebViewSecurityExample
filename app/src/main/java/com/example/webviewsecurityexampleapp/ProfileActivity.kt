package com.example.webviewsecurityexampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvUserName = findViewById<TextView>(R.id.tvUserName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)

        // In a real app, you'd load dynamic user data here.
        // For demo purposes:
        tvUserName.text = "John Doe"
        tvEmail.text = "john.doe@example.com"

        findViewById<Button>(R.id.btnEditProfile).setOnClickListener {
            Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
