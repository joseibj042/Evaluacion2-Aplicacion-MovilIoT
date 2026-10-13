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
            val ipAddress = binding.etIpAddress.text.toString()
            val nombreSala = binding.etRoomName.text.toString()

            if (ipAddress.isNotEmpty() && nombreSala.isNotEmpty()) {
                Toast.makeText(this, "ESP32 '$nombreSala' vinculado a Firebase", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}