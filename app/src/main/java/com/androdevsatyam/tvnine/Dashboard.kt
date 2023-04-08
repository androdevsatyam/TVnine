package com.androdevsatyam.tvnine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Dashboard : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var email: TextView
    lateinit var name: TextView
    lateinit var signout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.email)
        name = findViewById(R.id.name)


        name.text = auth.currentUser?.displayName
        email.text = auth.currentUser?.email


        signout = findViewById(R.id.signout)

        signout.setOnClickListener({
            auth.signOut()
            intent =Intent(this, Splash::class.java)
            startActivity(intent)
            finish()
        })


    }
}