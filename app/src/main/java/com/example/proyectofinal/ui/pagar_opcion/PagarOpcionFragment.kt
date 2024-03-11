package com.example.proyectofinal.ui.pagar_opcion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentPagarOpcionBinding

class PagarOpcionFragment : Fragment() {

    private var _binding: FragmentPagarOpcionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPagarOpcionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val opcionseleccionada: TextView = binding.frPagarOpcionBoton
        opcionseleccionada.setOnClickListener() {
            findNavController().navigate(PagarOpcionFragmentDirections.actionNavPagarOpcionToNavPagarConfirmado())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}