package com.example.proyectofinal.ui.ordenar_opcion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentOrdenarOpcionBinding
import com.example.proyectofinal.modelo_carrito.Carrito
import com.example.proyectofinal.modelo_carrito.Proveedora

class OrdenarOpcionFragment : Fragment(){

    private var _binding: FragmentOrdenarOpcionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdenarOpcionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // BLOQUE 1
        val args = OrdenarOpcionFragmentArgs.fromBundle(requireArguments())
        val opcion = args.opcion
        val visible = args.visible
        val img = args.img

        val titulo: TextView = binding.frOrdenarOpcionTitulo
        val subcontenedor1: LinearLayout = binding.frOrdenarOpcionSubcontenedor1
        val subcontenedor2: LinearLayout = binding.frOrdenarOpcionSubcontenedor2

        titulo.text = opcion
        subcontenedor1.isVisible = visible
        subcontenedor2.isVisible = !visible

        var precio = when(opcion) {
            "Tacos" -> 14
            "Tortas" -> 50
            "Burritos" -> 65
            "Bebidas" -> 25
            else -> 0
        }

        val agregarCarrito: TextView = binding.frOrdenarOpcionBoton

        // BLOQUE 2
        if (subcontenedor1.isVisible) {
            // BLOQUE 2.1

            val cantidadBi: TextView = binding.textoCantidadBi
            val cantidadCa: TextView = binding.textoCantidadCa
            val cantidadCo: TextView = binding.textoCantidadCo

            val masunoBi: ImageButton = binding.cantidadMas1Bi
            val masunoCo: ImageButton = binding.cantidadMas1Co
            val masunoCa: ImageButton = binding.cantidadMas1Ca

            val menosunoBi: ImageButton = binding.cantidadMenos1Bi
            val menosunoCo: ImageButton = binding.cantidadMenos1Co
            val menosunoCa: ImageButton = binding.cantidadMenos1Ca


            masunoBi.setOnClickListener() {
                sumarUno(cantidadBi, cantidadCo, cantidadCa)
            }

            masunoCo.setOnClickListener() {
                sumarUno(cantidadCo, cantidadBi, cantidadCa)
            }

            masunoCa.setOnClickListener() {
                sumarUno(cantidadCa, cantidadBi, cantidadCo)
            }

            menosunoBi.setOnClickListener() {
                restarUno(cantidadBi)
            }

            menosunoCo.setOnClickListener() {
                restarUno(cantidadCo)
            }

            menosunoCa.setOnClickListener() {
                restarUno(cantidadCa)
            }


            // BLOQUE 2.2
            val check1: CheckBox = binding.frOrdenarOpcionSeccionOpcion1
            val check2: CheckBox = binding.frOrdenarOpcionSeccionOpcion2
            val check3: CheckBox = binding.frOrdenarOpcionSeccionOpcion3

            val cantidadbi: RelativeLayout = binding.cantidadBi
            val cantidadco: RelativeLayout = binding.cantidadCo
            val cantidadca: RelativeLayout = binding.cantidadCa

            check1.setOnClickListener() {
                isAplicable(cantidadbi, check1, cantidadBi, totalUnidades(cantidadBi, cantidadCo, cantidadCa))
            }

            check2.setOnClickListener() {
                isAplicable(cantidadco, check2, cantidadCo, totalUnidades(cantidadBi, cantidadCo, cantidadCa))
            }

            check3.setOnClickListener() {
                isAplicable(cantidadca, check3, cantidadCa, totalUnidades(cantidadBi, cantidadCo, cantidadCa))
            }

            // BLOQUE 5
            agregarCarrito.setOnClickListener() {
                val cantidad: Int = totalUnidades(cantidadBi, cantidadCo, cantidadCa)
                precio *= cantidad
                val item = Carrito(opcion, precio, cantidad, img)
                Proveedora.agregarPedido(item)

                pedidoAgregado()
            }
        }
        else {

            // BLOQUE 2.3
            val bebidas = listOf(binding.frOrdenarOpcionBebida1, binding.frOrdenarOpcionBebida2,
                                 binding.frOrdenarOpcionBebida3, binding.frOrdenarOpcionBebida4,
                                 binding.frOrdenarOpcionBebida5, binding.frOrdenarOpcionBebida6)
            var cantidad = 0

            for (bebida in bebidas) {
                bebida.setOnClickListener() {   if (bebida.isChecked) cantidad += 1   }
            }

            agregarCarrito.setOnClickListener() {
                precio *= cantidad
                val item = Carrito(opcion, precio, cantidad, img)
                Proveedora.agregarPedido(item)
                pedidoAgregado()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun totalUnidades(bi: TextView, co: TextView, ca: TextView): Int {
        val cantidadbi = bi.text.toString().toInt()
        val cantidadco = co.text.toString().toInt()
        val cantidadca = ca.text.toString().toInt()
        return cantidadbi + cantidadco + cantidadca
    }

    fun sumarUno(tview: TextView, tview2: TextView, tview3: TextView) {
        var cantidad: Int = tview.text.toString().toInt()

        if ((totalUnidades(tview, tview2, tview3) + 1) > 6) {
            Toast.makeText(context, "Maximo 6 por orden", Toast.LENGTH_SHORT).show()
        }
        else {
            cantidad += 1
            tview.text = cantidad.toString()
        }
    }

    fun restarUno(tview: TextView) {
        var cantidad: Int = tview.text.toString().toInt()

        if (cantidad - 1 < 1) {
            Toast.makeText(context, "intente desmarcando la opcion", Toast.LENGTH_SHORT).show()
        }
        else {
            tview.text = (cantidad - 1).toString()
        }
    }

    fun isAplicable(relay: RelativeLayout, che: CheckBox, tview: TextView, total: Int) {

        if (che.isChecked) {
            relay.isVisible = true
            tview.text = "1"
        }
        else {
            relay.isVisible = false
            tview.text = "0"
        }

    }

    fun pedidoAgregado() {
        Toast.makeText(context, "Pedido agregado!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(OrdenarOpcionFragmentDirections.actionNavOrdenarOpcionToNavOrdenar())
    }

}