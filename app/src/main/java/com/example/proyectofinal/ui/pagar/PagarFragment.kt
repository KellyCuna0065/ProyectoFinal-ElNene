package com.example.proyectofinal.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentPagarBinding

class PagarFragment : Fragment() {

    private var _binding: FragmentPagarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPagarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navcontroller = findNavController()

        val radiobtn1: RadioButton = binding.frPagarOpcion1
        val radiobtn2: RadioButton = binding.frPagarOpcion2
        val radiobtn3: RadioButton = binding.frPagarOpcion3

        radiobtn1.setOnClickListener() {
            navcontroller.navigate(PagarFragmentDirections.actionNavPagarToNavPagarOpcion())
        }

        radiobtn2.setOnClickListener() {
            navcontroller.navigate(PagarFragmentDirections.actionNavPagarToNavPagarConfirmado())
        }

        radiobtn3.setOnClickListener() {
            navcontroller.navigate(PagarFragmentDirections.actionNavPagarToNavPagarConfirmado())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

