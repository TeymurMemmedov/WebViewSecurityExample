package com.example.webviewsecurityexampleapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PaymentErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_error)

        // Retry button (you can redirect back to payment or reload WebView)
        findViewById<Button>(R.id.btnRetryPayment).setOnClickListener {
            finish() // or startActivity(Intent(this, PaymentActivity::class.java))
        }

        // Back to home
        findViewById<Button>(R.id.btnErrorBack).setOnClickListener {
            finish() // or go back to MainActivity
        }
    }
}
