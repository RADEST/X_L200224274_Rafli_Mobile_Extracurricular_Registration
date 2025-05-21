package com.example.extracurricularregistration

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nama = intent.getStringExtra("nama") ?: "-"
        val nis = intent.getStringExtra("nis") ?: "-"
        val kelas = intent.getStringExtra("kelas") ?: "-"
        val hariJam = intent.getStringExtra("hariJam") ?: "-"

        findViewById<TextView>(R.id.tvNama).text = getString(R.string.label_nama, nama)
        findViewById<TextView>(R.id.tvNIS).text = getString(R.string.label_nis, nis)
        findViewById<TextView>(R.id.tvKelas).text = getString(R.string.label_kelas, kelas)
        findViewById<TextView>(R.id.tvHariJam).text = getString(R.string.label_hari_jam, hariJam)

        findViewById<Button>(R.id.btnKembali).setOnClickListener {
            finish()
        }
    }
}
