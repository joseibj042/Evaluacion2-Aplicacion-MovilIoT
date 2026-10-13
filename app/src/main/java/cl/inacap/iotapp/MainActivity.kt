package cl.inacap.iotapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.inacap.iotapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
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

        leerSensoresDesdeFirebase()

        binding.btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.btnAgregarDispositivo.setOnClickListener {
            startActivity(Intent(this, AddDeviceActivity::class.java))
        }
    }

    private fun leerSensoresDesdeFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Leemos los valores que envíe el ESP32 (o los que pongamos a mano para probar)
                val temp = snapshot.child("temperatura").getValue(Double::class.java) ?: 0.0
                val puertaAbierta = snapshot.child("puertaAbierta").getValue(Boolean::class.java) ?: false

                actualizarUI(temp, puertaAbierta)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(baseContext, "Error leyendo base de datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarUI(temperatura: Double, puertaAbierta: Boolean) {
        binding.tvTemperatura.text = "$temperatura °C"

        if (puertaAbierta) {
            binding.tvEstadoPuerta.text = "ALERTA: ABIERTA"
            binding.tvEstadoPuerta.setTextColor(getColor(android.R.color.holo_red_dark))
        } else {
            binding.tvEstadoPuerta.text = "CERRADA (Seguro)"
            binding.tvEstadoPuerta.setTextColor(getColor(android.R.color.holo_green_dark))
        }

        if (temperatura > 30.0) {
            binding.tvTemperatura.setTextColor(getColor(android.R.color.holo_red_dark))
        } else {
            binding.tvTemperatura.setTextColor(getColor(android.R.color.tab_indicator_text)) // Color normal
        }
    }
}