package com.example.pertemuan3_tia

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ReportHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_history)
        val toolbar = findViewById<Toolbar>(R.id.historyToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Riwayat Laporan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val reports = getSharedPreferences("user_pref", MODE_PRIVATE)
            .getStringSet("reports", emptySet())?.toList().orEmpty()
        findViewById<TextView>(R.id.tvReports).text =
            if (reports.isEmpty()) "Belum ada laporan.\nSilakan buat laporan dari menu Laporkan Masalah."
            else reports.mapIndexed { i, report -> "${i + 1}. $report" }.joinToString("\n\n")
    }
}
