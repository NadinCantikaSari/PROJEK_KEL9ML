package com.example.pertemuan3_tia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan3_tia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.edrUser.text.toString().trim()
            val pass = binding.edtPassword.text.toString()

            if (user.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show()
            } else if (user == "admin" && pass == "12345") {
                getSharedPreferences("user_pref", MODE_PRIVATE).edit()
                    .putBoolean("isLogin", true)
                    .putString("username", user)
                    .apply()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
