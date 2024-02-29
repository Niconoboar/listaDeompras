package com.tudominio.tuapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Producto(val nombre: String, val cantidad: Int, val precio: Double)

class ProductoAdapter(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.nombreTextView.text = producto.nombre
        holder.cantidadTextView.text = producto.cantidad.toString()
        holder.precioTextView.text = producto.precio.toString()
    }

    override fun getItemCount(): Int = listaProductos.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val cantidadTextView: TextView = itemView.findViewById(R.id.cantidadTextView)
        val precioTextView: TextView = itemView.findViewById(R.id.precioTextView)
    }
}

class MainActivity : AppCompatActivity() {

    private val listaProductos = listOf(
        Producto("Producto 1", 1, 10.0),
        Producto("Producto 2", 2, 20.0),
        Producto("Producto 3", 3, 30.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ProductoAdapter(listaProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val pagarButton = findViewById<Button>(R.id.pagarButton)
        pagarButton.setOnClickListener {
            val intent = Intent(this, ResumenPagoActivity::class.java) // Corrected class name
            intent.putExtra("listaProductos", listaProductos)
            startActivity(intent)
        }
    }
}

