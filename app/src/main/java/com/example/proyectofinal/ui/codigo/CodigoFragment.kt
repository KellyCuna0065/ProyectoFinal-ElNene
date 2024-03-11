package com.example.proyectofinal.ui.codigo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentCodigoBinding
import com.example.proyectofinal.modelo_pedido.PedidoProveedora
import com.example.proyectofinal.modelo_pedido.PedidoProveedora.Companion.pedidoList

class CodigoFragment : Fragment() {

    private var _binding: FragmentCodigoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCodigoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bloque(PedidoProveedora.getCodigo(pedidoList.size - 1))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun bloque(codigo: String) {
        val codview: TextView = binding.frCodigoCampo1
        codview.text = codigo

        val contenedor1: ConstraintLayout = binding.frCodigoTrue
        val contenedor2: ConstraintLayout = binding.frCodigoFalse

        if (codigo == "0") {
            contenedor2.isVisible = true
            contenedor1.isVisible = false

            val ordenar: TextView = binding.frCodigoComprar
            ordenar.setOnClickListener() {
                findNavController().navigate(CodigoFragmentDirections.actionNavCodigoToNavOrdenar())
            }
        }
        else {
            contenedor1.isVisible = true
            contenedor2.isVisible = false

            val pedidorecogido: TextView = binding.frCodigoRecogido
            pedidorecogido.setOnClickListener(){
                PedidoProveedora.eliminarPedido(pedidoList.size - 1)
                codview.text = PedidoProveedora.getCodigo(pedidoList.size - 1)
                Toast.makeText(context, "Gracias por su compra!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(CodigoFragmentDirections.actionNavCodigoToNavPrincipal())
            }
        }
    }

}