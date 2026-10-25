package com.example.pertemuan3_tia

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val toolbar = findViewById<Toolbar>(R.id.reportToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Laporkan Masalah"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val facility = intent.getStringExtra("facilityName")
        val description = findViewById<EditText>(R.id.etReportDescription)
        if (!facility.isNullOrBlank()) description.setText("Fasilitas: $facility\n")
        val spinner = findViewById<Spinner>(R.id.spReportType)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Pilih Jenis Kerusakan", "Fasilitas rusak", "Kebersihan", "Lampu mati", "Lainnya"))

        findViewById<Button>(R.id.btnSubmitReport).setOnClickListener {
            val type = spinner.selectedItem.toString()
            val text = description.text.toString().trim()
            if (spinner.selectedItemPosition == 0 || text.isBlank()) {
                Toast.makeText(this, "Pilih jenis masalah dan isi deskripsi", Toast.LENGTH_SHORT).show()
            } else {
                val prefs = getSharedPreferences("user_pref", MODE_PRIVATE)
                val old = prefs.getStringSet("reports", emptySet())?.toMutableSet() ?: mutableSetOf()
                old.add("$type | $text")
                prefs.edit().putStringSet("reports", old).apply()
                Toast.makeText(this, "Laporan berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
