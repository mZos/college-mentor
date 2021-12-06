package com.example.kipmnotes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kipmnotes.R

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        handler = Handler()
        handler.postDelayed({
           val intent = Intent(this,LoginActivity::class.java)
           startActivity(intent)
        },2000)

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}