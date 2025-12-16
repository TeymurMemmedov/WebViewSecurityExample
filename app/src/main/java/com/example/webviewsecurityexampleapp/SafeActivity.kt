package com.example.webviewsecurityexampleapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class SafeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webView)

        val settings = webView.settings
        settings.javaScriptEnabled = true  // ok for trusted use-cases
        settings.domStorageEnabled = true

        // ✅ Secure bridge with strict validation + limited capabilities
        webView.addJavascriptInterface(AndroidBridgeSecure(this), "NativeApp")

        webView.loadUrl("file:///android_asset/malicious_page.html")
    }
}

class AndroidBridgeSecure(val context: Context) {

    @JavascriptInterface
    fun logEvent(jsonString: String) {
        try {
            // Validate input before parsing
            if (!jsonString.trim().startsWith("{")) return

            val obj = JSONObject(jsonString)

            // Only allow specific whitelisted event names
            val event = obj.optString("event", "")

            val allowedEvents = listOf("page_open", "button_click")
            if (event !in allowedEvents) return

            Log.d("SecureAnalytics", "Valid event: $event")

        } catch (e: Exception) {
            // Never crash
            Log.e("SecureAnalytics", "Rejected malicious event")
        }
    }

    @JavascriptInterface
    fun openScreen(screen: String):String {
        val allowedScreens = listOf("payment_success", "payment_error")
        if (screen in allowedScreens) {
            context.startActivity(
                Intent(
                    context,
                    getScreenClassFromString(screen)
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })

            return "OK"
        }else{
            return "ERROR:NOT_ALLOWED"
        }
    }
}

