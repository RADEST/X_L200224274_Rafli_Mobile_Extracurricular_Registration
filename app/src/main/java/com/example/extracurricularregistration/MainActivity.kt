package com.example.extracurricularregistration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerKelas: Spinner
    private lateinit var spinnerHari: Spinner
    private lateinit var spinnerJamMulai: Spinner
    private lateinit var spinnerJamSelesai: Spinner
    private lateinit var tvWaktuLengkap: TextView

    private var hariTerpilih = ""
    private var jamMulaiTerpilih = ""
    private var jamSelesaiTerpilih = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi komponen UI
        spinnerKelas = findViewById(R.id.spinnerKelas)
        spinnerHari = findViewById(R.id.spinnerHari)
        spinnerJamMulai = findViewById(R.id.spinnerJamMulai)
        spinnerJamSelesai = findViewById(R.id.spinnerJamSelesai)
        tvWaktuLengkap = findViewById(R.id.tvWaktuLengkap)

        // Setup Spinner Kelas
        ArrayAdapter.createFromResource(
            this,
            R.array.kelas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerKelas.adapter = adapter
        }

        // Setup adapter untuk pilihan hari
        val hariOptions = arrayOf("Pilih Hari", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")
        val hariAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            hariOptions
        )
        hariAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHari.adapter = hariAdapter

        // Setup adapter untuk pilihan jam mulai
        val jamMulaiOptions = setupJamOptions("Mulai Jam")
        val jamMulaiAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            jamMulaiOptions
        )
        jamMulaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJamMulai.adapter = jamMulaiAdapter

        // Setup adapter untuk pilihan jam selesai
        val jamSelesaiOptions = setupJamOptions("Selesai Jam")
        val jamSelesaiAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            jamSelesaiOptions
        )
        jamSelesaiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJamSelesai.adapter = jamSelesaiAdapter

        // Set listener untuk Spinner Hari
        spinnerHari.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position > 0) { // Skip "Pilih Hari"
                    hariTerpilih = hariOptions[position]
                    updateWaktuLengkap()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set listener untuk Spinner Jam Mulai
        spinnerJamMulai.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position > 0) { // Skip "Mulai Jam"
                    jamMulaiTerpilih = jamMulaiOptions[position]
                    updateWaktuLengkap()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set listener untuk Spinner Jam Selesai
        spinnerJamSelesai.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position > 0) { // Skip "Selesai Jam"
                    jamSelesaiTerpilih = jamSelesaiOptions[position]
                    updateWaktuLengkap()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Setup Simpan Button
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            val nama = findViewById<EditText>(R.id.etNama).text.toString()
            val nis = findViewById<EditText>(R.id.etNIS).text.toString()
            val kelas = spinnerKelas.selectedItem.toString()
            val hariJam = getWaktuLengkap()

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

    // Metode untuk membuat array opsi jam
    private fun setupJamOptions(defaultText: String): Array<String> {
        val jamOptions = Array(25) { "" } // 24 jam + default text
        jamOptions[0] = defaultText

        for (i in 1 until jamOptions.size) {
            val hour = i - 1
            jamOptions[i] = String.format("%02d:00", hour)
        }

        return jamOptions
    }

    // Metode untuk mengupdate tampilan waktu lengkap
    private fun updateWaktuLengkap() {
        if (hariTerpilih.isNotEmpty() && jamMulaiTerpilih.isNotEmpty() && jamSelesaiTerpilih.isNotEmpty()) {
            val waktuLengkap = "$hariTerpilih $jamMulaiTerpilih - $jamSelesaiTerpilih"
            tvWaktuLengkap.text = "Hari dan Waktu: $waktuLengkap"
        }
    }

    // Metode untuk mendapatkan data waktu lengkap (dipanggil saat menyimpan data)
    private fun getWaktuLengkap(): String {
        return if (hariTerpilih.isNotEmpty() && jamMulaiTerpilih.isNotEmpty() && jamSelesaiTerpilih.isNotEmpty()) {
            "$hariTerpilih $jamMulaiTerpilih - $jamSelesaiTerpilih"
        } else {
            ""
        }
    }
}