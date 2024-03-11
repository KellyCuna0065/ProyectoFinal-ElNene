package com.example.proyectofinal.ui.carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentCarritoBinding
import com.example.proyectofinal.modelo_carrito.Adaptador
import com.example.proyectofinal.modelo_carrito.Proveedora
import com.example.proyectofinal.modelo_carrito.Proveedora.Companion.carritoList
import com.example.proyectofinal.modelo_pedido.ItemChangedListener

class CarritoFragment : Fragment(), ItemChangedListener {

    private var _binding: FragmentCarritoBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var contenedor1:ConstraintLayout
    private lateinit var contenedor2:ConstraintLayout
    private lateinit var total:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val pagar: TextView = binding.frCarritoPagar
        pagar.setOnClickListener(){
            findNavController().navigate(CarritoFragmentDirections.actionNavCarritoToNavPagar())
        }

        contenedor1 = binding.frCarritoContenedor1
        contenedor2 = binding.frCarritoContenedor2
        total = binding.frCarritoTotal

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.frCarritoRecview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = Adaptador(carritoList, this)

        contenedorVisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun contenedorVisible() {
        if (carritoList.isEmpty()) {
            contenedor2.isVisible = true
            contenedor1.isVisible = false
        }
        else {
            contenedor2.isVisible = false
            contenedor1.isVisible = true
            total.text = Proveedora.total().toString()
        }
    }

    override fun onItemChanged() {
        contenedorVisible()

        if (contenedor1.isVisible) {
            total.text = Proveedora.total().toString()
        }
    }

}