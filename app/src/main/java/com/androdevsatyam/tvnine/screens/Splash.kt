package com.androdevsatyam.tvnine.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.androdevsatyam.tvnine.R
import com.androdevsatyam.tvnine.helpers.Global
import com.facebook.FacebookSdk
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Splash : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var analytics: FirebaseAnalytics
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        analytics = Firebase.analytics

    }

    override fun onResume() {
        super.onResume()
        val intent: Intent
        if (auth.currentUser == null) {
            bundle=Bundle()
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "LoginUser")
            Global.logs(FirebaseAnalytics.Event.APP_OPEN, bundle, analytics)
            intent = Intent(this, MainActivity::class.java)
        } else {
            bundle=Bundle()
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "NewUser")
            Global.logs(FirebaseAnalytics.Event.APP_OPEN, bundle, analytics)
            intent = Intent(this, Dashboard::class.java)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 1500)


    }
}