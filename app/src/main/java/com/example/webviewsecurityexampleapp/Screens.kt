package com.example.webviewsecurityexampleapp

import android.app.Activity
import android.content.Intent

fun getScreenClassFromString(value:String) = when(
    value
){
    "payment_success" -> PaymentSuccessActivity::class.java
    "payment_error" -> PaymentErrorActivity::class.java
    "profile" -> ProfileActivity::class.java
    else-> null
}
