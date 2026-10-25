package com.example.pertemuan3_tia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.homeToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Smart Public Facility"

        val username = getSharedPreferences("user_pref", MODE_PRIVATE)
            .getString("username", "Pengguna")
        findViewById<TextView>(R.id.tvWelcome).text = "Halo, $username!"

        findViewById<EditText>(R.id.etSearchFacility).setOnEditorActionListener { v, _, _ ->
            val q = v.text.toString().trim()
            if (q.isNotEmpty()) {
                startActivity(Intent(this, FacilityListActivity::class.java).putExtra("query", q))
            }
            true
        }

        findViewById<Button>(R.id.btnAllFacilities).setOnClickListener {
            startActivity(Intent(this, FacilityListActivity::class.java))
        }
        findViewById<Button>(R.id.btnRecommendedDetail).setOnClickListener {
            startActivity(Intent(this, FacilityDetailActivity::class.java).putExtra("facilityName", "Taman Kota Pekanbaru"))
        }
        findViewById<Button>(R.id.btnReport).setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
        findViewById<Button>(R.id.btnHistory).setOnClickListener {
            startActivity(Intent(this, ReportHistoryActivity::class.java))
        }
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apakah kamu yakin ingin logout?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Ya") { _, _ ->
                    getSharedPreferences("user_pref", MODE_PRIVATE).edit().clear().apply()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }.show()
        }
    }
}
