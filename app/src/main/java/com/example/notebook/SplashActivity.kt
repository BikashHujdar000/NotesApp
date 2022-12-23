package com.example.notebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.github.ybq.android.spinkit.style.Wave

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        val progressBar = findViewById<View>(R.id.spin) as ProgressBar
        val threeBounce = ThreeBounce()
        progressBar.indeterminateDrawable =threeBounce



        Handler(Looper.getMainLooper()).postDelayed(
            Runnable{

                startActivity(Intent(this,MainActivity::class.java))
            }, 1000)

    }
}