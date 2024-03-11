package com.example.proyectofinal.modelo_carrito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.CardCarritoBinding
import com.example.proyectofinal.modelo_pedido.ItemChangedListener

class Adaptador (private val productosList: List<Carrito>,
                 private val itemChangeListener: ItemChangedListener) : RecyclerView.Adapter<Adaptador.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = CardCarritoBinding.inflate(layoutInflator, parent, false)
        return MyViewHolder(binding, itemChangeListener)
    }

    override fun getItemCount(): Int = productosList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = productosList[position]
        holder.bind(item)
    }

    class MyViewHolder(private val binding: CardCarritoBinding, private val itemChangeListener: ItemChangedListener) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: Carrito) {
            binding.cardCarritoTitulo.text = pedido.comida
            binding.cardCarritoPrecio.text = pedido.precio.toString()
            binding.cardCarritoCantidad.text = pedido.cantidad.toString()
            binding.cardCarritoImg.setImageResource(pedido.img)

            binding.cardCarritoEliminar.setOnClickListener() {
                Proveedora.eliminarPedido(pedido)
                itemChangeListener.onItemChanged()
                bindingAdapter?.notifyDataSetChanged()
            }
        }
    }
}