package com.example.proyectofinal.ui.ordenar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentOrdenarBinding
import com.example.proyectofinal.R


class OrdenarFragment : Fragment(){

    private var _binding: FragmentOrdenarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdenarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val opcionList = listOf(binding.frOrdenarContenedor1, binding.frOrdenarContenedor2,
                                binding.frOrdenarContenedor3, binding.frOrdenarContenedor4)

        for (i in opcionList.indices) {
            opcionList[i].setOnClickListener() {  sendData(i)  }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun sendData (numopcion: Int) {
        var opcionseleccionada: String
        var visibilidad: Boolean
        var img = 0
        when (numopcion) {
            0 -> {
                opcionseleccionada = "Tacos"
                visibilidad = true
                img = R.drawable.taco
            }
            1 -> {
                opcionseleccionada = "Tortas"
                visibilidad = true
                img = R.drawable.torta
            }
            2 -> {
                opcionseleccionada = "Burritos"
                visibilidad = true
                img = R.drawable.burrito
            }
            3 -> {
                opcionseleccionada = "Bebidas"
                visibilidad = false
                img = R.drawable.bebidas
            }
            else -> {
                opcionseleccionada = "Tacos"
                visibilidad = false
            }
        }

        val action = OrdenarFragmentDirections.actionNavOrdenarToNavOrdenarOpcion(
            opcion = opcionseleccionada,
            visible = visibilidad,
            img = img
        )
        findNavController().navigate(action)
    }

}