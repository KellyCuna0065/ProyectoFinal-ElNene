package com.example.proyectofinal.ui.pagar_confirmado

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentPagarConfirmadoBinding
import com.example.proyectofinal.modelo_carrito.Proveedora
import com.example.proyectofinal.modelo_pedido.Pedido
import com.example.proyectofinal.modelo_pedido.PedidoProveedora

class PagarConfirmadoFragment : Fragment() {

    private var _binding: FragmentPagarConfirmadoBinding? = null
    private val binding get() = _binding!!

    var cod: String = (1000 .. 9999).random().toString()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPagarConfirmadoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tiempoespera: TextView = binding.frPagarConfirmadoCampo1
        var minutos: Int
        var horas: Int

        minutos = Proveedora.tiempoEspera()

        if (minutos >= 60) {
            horas = minutos / 60
            minutos %= 60

            tiempoespera. text = if (minutos == 0) "$horas hora(s)" else "$horas hora(s) $minutos minuto(s)"
        }
        else {
            tiempoespera.text = "$minutos minuto(s)"
        }

        val codigo: TextView = binding.frPagarConfirmadoCampo2
        codigo.text = cod

        val textview: TextView = binding.frPagarConfirmadoBoton
        textview.setOnClickListener() {
            Proveedora.limpiarPedidos()
            val item = Pedido(cod)
            PedidoProveedora.agregarPedido(item)
            Toast.makeText(context, "Transaccion exitosa!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(PagarConfirmadoFragmentDirections.actionNavPagarConfirmadoToNavPrincipal())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}