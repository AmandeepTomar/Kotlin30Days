package com.example.kotlin30days.view.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin30days.R
import com.example.kotlin30days.utility.start
import com.example.kotlin30days.view.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            start(HomeActivity::class.java)
            finish()
        }, 1000)
    }
}