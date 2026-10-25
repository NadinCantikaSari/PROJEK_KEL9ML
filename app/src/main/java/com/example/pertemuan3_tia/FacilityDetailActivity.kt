package com.example.pertemuan3_tia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FacilityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility_detail)
        val toolbar = findViewById<Toolbar>(R.id.detailToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detail Fasilitas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val name = intent.getStringExtra("facilityName") ?: "Taman Kota Pekanbaru"
        findViewById<TextView>(R.id.tvFacilityName).text = name
        findViewById<Button>(R.id.btnReportFacility).setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java).putExtra("facilityName", name))
        }
    }
}
