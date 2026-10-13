package cl.inacap.iotapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cl.inacap.iotapp.databinding.ActivityHistoryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var database: DatabaseReference
    private val listaAlertas = mutableListOf<Alerta>()
    private lateinit var adapter: AlertasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AlertasAdapter(listaAlertas)
        binding.rvHistorial.layoutManager = LinearLayoutManager(this)
        binding.rvHistorial.adapter = adapter

        database = Firebase.database.reference.child("Alertas")
        cargarAlertasDesdeFirebase()
    }

    private fun cargarAlertasDesdeFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaAlertas.clear()

                for (alertaSnapshot in snapshot.children) {
                    val alerta = alertaSnapshot.getValue(Alerta::class.java)
                    if (alerta != null) {
                        listaAlertas.add(alerta)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(baseContext, "Error al cargar historial", Toast.LENGTH_SHORT).show()
            }
        })
    }
}