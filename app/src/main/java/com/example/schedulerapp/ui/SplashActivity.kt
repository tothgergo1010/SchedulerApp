package com.example.schedulerapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulerapp.AppLocaleManager
import com.example.schedulerapp.R

class SplashActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val localeManager = AppLocaleManager(newBase)
        val lang = localeManager.getLanguage()
        val context = localeManager.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}
