package com.example.pertemuan3_tia

import Pertemuan6.EnamActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan3_tia.databinding.ActivityLoginBinding
import com.example.pertemuan3_tia.databinding.ActivityMainBinding
import com.example.pertemuan3_tia.pertemuan5.LimaActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val user = intent.getStringExtra ("Username")
        val pass = intent.getStringExtra("Password")

        binding.textView.text = user
        binding.textView2.setText(pass)

        binding.SnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Halo ini snackbar",
                Snackbar.LENGTH_LONG)
                .setAction("KEMBALI") {
                    // kembalikan item
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Kembali ke halaman Activity", Toast.LENGTH_LONG).show()
                }
                .show()
        }
        binding.Alert.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Hapus data")
                .setMessage("Data yang dihapus tidak " +
                        "bisa dikembalikan.")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Hapus") { dialog, _ ->
                    // proses hapus
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Logout")
                .setMessage("Apakah yakin mau Keluar?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("keluar") { dialog, _ ->
                    val intent= Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                .setCancelable(false)
                .show()
        }
        binding.btnDetail.setOnClickListener {
            val intent= Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        binding.btnToLima.setOnClickListener {
            startActivity(Intent ( this, LimaActivity::class.java))
        }
        binding.btnToEnam.setOnClickListener {
            startActivity(Intent(this, EnamActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

}