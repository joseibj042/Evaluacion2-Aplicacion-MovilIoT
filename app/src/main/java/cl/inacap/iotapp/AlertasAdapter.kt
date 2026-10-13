package cl.inacap.iotapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Alerta(
    val fecha: String = "",
    val descripcion: String = "",
    val ubicacion: String = ""
)

class AlertasAdapter(private val alertas: List<Alerta>) : RecyclerView.Adapter<AlertasAdapter.AlertaViewHolder>() {

    class AlertaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val tvUbicacion: TextView = view.findViewById(R.id.tvUbicacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alerta, parent, false)
        return AlertaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertaViewHolder, position: Int) {
        val alerta = alertas[position]
        holder.tvFecha.text = alerta.fecha
        holder.tvDescripcion.text = alerta.descripcion
        holder.tvUbicacion.text = alerta.ubicacion
    }

    override fun getItemCount() = alertas.size
}