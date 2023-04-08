package com.androdevsatyam.tvnine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    val TAG = "SPLASH"
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        auth = FirebaseAuth.getInstance()

    }

    override fun onResume() {
        super.onResume()
        val intent:Intent
        if (auth.currentUser == null) {
            intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            intent =Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()
        }
    }
}