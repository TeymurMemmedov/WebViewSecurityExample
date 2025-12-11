package com.example.webviewsecurityexampleapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class VulnerableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webView)

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true

        // ❌ BAD: exposing powerful, unvalidated function to JS
        webView.addJavascriptInterface(AndroidBridgeInsecure(this), "NativeApp")

        webView.loadUrl("file:///android_asset/malicious_page.html")
    }
}

class AndroidBridgeInsecure(val context: Context) {

    @JavascriptInterface
    fun logEvent(jsonString: String) {
        // ❌ Developer trusts the JS to send proper JSON
        // Malicious JS can crash this using malformed or huge data
        val obj = JSONObject(jsonString)
        val event = obj.getString("event")

        Log.d("InsecureAnalytics", "Logged event: $event")
    }

    @JavascriptInterface
    fun openScreen(screen: String) {
        context.startActivity(
            Intent(
                context,
                getScreenClassFromString(screen)
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
    }
}


