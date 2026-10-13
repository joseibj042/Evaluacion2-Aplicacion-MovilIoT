package cl.inacap.iotapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cl.inacap.iotapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alertas = listOf(
            Alerta("13 Oct 2026 - 14:30", "Temperatura Crítica: 32.1°C", "Sala Servidores"),
            Alerta("12 Oct 2026 - 09:15", "Apertura de Puerta No Autorizada", "Acceso Principal"),
            Alerta("10 Oct 2026 - 18:45", "Temperatura Elevada: 30.5°C", "Sala Servidores"),
            Alerta("08 Oct 2026 - 22:10", "Apertura de Puerta No Autorizada", "Acceso Principal")
        )

        val adapter = AlertasAdapter(alertas)
        binding.rvHistorial.layoutManager = LinearLayoutManager(this)
        binding.rvHistorial.adapter = adapter
    }
}