package com.example.pertemuan3_tia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(1800)
            val isLogin = getSharedPreferences("user_pref", MODE_PRIVATE)
                .getBoolean("isLogin", false)
            val destination = if (isLogin) HomeActivity::class.java else LoginActivity::class.java
            startActivity(Intent(this@SplashScreenActivity, destination))
            finish()
        }
    }
}
