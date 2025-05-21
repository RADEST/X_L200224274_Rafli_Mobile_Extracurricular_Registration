package com.example.extracurricularregistration

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Spinner
        val spinner = findViewById<Spinner>(R.id.spinnerKelas)
        ArrayAdapter.createFromResource(
            this,
            R.array.kelas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Setup Simpan Button
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            val nama = findViewById<EditText>(R.id.etNama).text.toString()
            val nis = findViewById<EditText>(R.id.etNIS).text.toString()
            val kelas = spinner.selectedItem.toString()
            val hariJam = findViewById<EditText>(R.id.etHariJam).text.toString()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("nis", nis)
                putExtra("kelas", kelas)
                putExtra("hariJam", hariJam)
            }
            startActivity(intent)
        }

        // Setup Batal Button
        val btnBatal = findViewById<Button>(R.id.btnBatal)
        btnBatal.setOnClickListener {
            finish()
        }
    }
}