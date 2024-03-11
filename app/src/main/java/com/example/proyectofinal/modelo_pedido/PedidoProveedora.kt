package com.example.proyectofinal.modelo_pedido

class PedidoProveedora {
    companion object {
        val pedidoList = mutableListOf<Pedido>(
            Pedido("0")
        )

        fun agregarPedido(item: Pedido) {
            pedidoList.add(item)
        }

        fun eliminarPedido(i: Int) {
            pedidoList.removeAt(i)
        }

        fun getCodigo(i: Int): String {
            return pedidoList[i].codigo
        }
    }
}