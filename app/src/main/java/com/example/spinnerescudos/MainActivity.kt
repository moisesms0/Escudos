package com.example.spinnerescudos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val textEquipo = findViewById<TextView>(R.id.textEquipo)

        val escudos = listOf(
            Escudo("Tenerife", R.drawable.cdtenerife),
            Escudo("Atletico", R.drawable.atletico),
            Escudo("Barça", R.drawable.barsa),
            Escudo("Las Palmas", R.drawable.laspalmas)
        )

        val adapter = PersonajeAdapter(this, R.layout.escudos_spinner, escudos)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedEscudo = escudos[position]
                textEquipo.text = "Seleccionado: ${selectedEscudo.nombre}"
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }




    }



}

data class Escudo(val nombre: String, val img: Int)

class PersonajeAdapter(context: Context, resource: Int, private val escudos: List<Escudo>) :
    ArrayAdapter<Escudo>(context, resource, escudos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.escudos_spinner, parent, false)

        val escudo = getItem(position)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val imagenImageView: ImageView = itemView.findViewById(R.id.imagenImageView)

        nombreTextView.text = escudo?.nombre
        imagenImageView.setImageResource(escudo?.img ?: 0)

        return itemView
    }
}