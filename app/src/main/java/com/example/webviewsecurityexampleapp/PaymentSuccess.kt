package com.example.webviewsecurityexampleapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_success)

        // Button: Back to Home
        findViewById<Button>(R.id.btnSuccessBack).setOnClickListener {
            finish() // or startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
