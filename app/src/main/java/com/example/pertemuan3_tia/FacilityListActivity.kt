package com.example.pertemuan3_tia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FacilityListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility_list)
        val toolbar = findViewById<Toolbar>(R.id.facilityToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Daftar Fasilitas"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val query = intent.getStringExtra("query")
        val buttons = listOf(
            R.id.btnPark to "Taman Kota Pekanbaru",
            R.id.btnAlamMayang to "Taman Alam Mayang",
            R.id.btnHospital to "Rumah Sakit Umum",
            R.id.btnSport to "Lapangan Padel"
        )
        buttons.forEach { (id, name) ->
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, FacilityDetailActivity::class.java).putExtra("facilityName", name))
            }
        }
        if (!query.isNullOrBlank()) {
            title = "Hasil: $query"
        }
    }
}
