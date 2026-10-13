package cl.inacap.iotapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.inacap.iotapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference.child("Sensores")

        // Emulación de datos de sensores para la Evaluación 1 (Octubre 2026)
        val temperaturaActual = 24.5
        val puertaAbierta = false

        binding.tvTemperatura.text = "$temperaturaActual °C"

        if (puertaAbierta) {
            binding.tvEstadoPuerta.text = "ALERTA: ABIERTA"
            binding.tvEstadoPuerta.setTextColor(getColor(android.R.color.holo_red_dark))
        } else {
            binding.tvEstadoPuerta.text = "CERRADA (Seguro)"
            binding.tvEstadoPuerta.setTextColor(getColor(android.R.color.holo_green_dark))
        }

        if (temperaturaActual > 30.0) {
            binding.tvTemperatura.setTextColor(getColor(android.R.color.holo_red_dark))
        }

        binding.btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.btnAgregarDispositivo.setOnClickListener {
            startActivity(Intent(this, AddDeviceActivity::class.java))
        }
    }
}