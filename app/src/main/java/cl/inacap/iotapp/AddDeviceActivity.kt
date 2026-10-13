package cl.inacap.iotapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.inacap.iotapp.databinding.ActivityAddDeviceBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddDeviceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDeviceBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase.database.reference.child("Dispositivos")

        binding.btnGuardarDispositivo.setOnClickListener {
            val ipAddress = binding.etIpAddress.text.toString().trim()
            val nombreSala = binding.etRoomName.text.toString().trim()

            if (ipAddress.isNotEmpty() && nombreSala.isNotEmpty()) {
                val nuevoDispositivo = mapOf(
                    "ip" to ipAddress,
                    "ubicacion" to nombreSala
                )

                database.push().setValue(nuevoDispositivo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Dispositivo guardado en Firebase", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al guardar en la base de datos", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}