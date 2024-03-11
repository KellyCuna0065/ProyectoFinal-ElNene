package com.example.proyectofinal.modelo_carrito

class Proveedora {
    companion object {
        val carritoList = mutableListOf<Carrito>()

        fun agregarPedido(item : Carrito) {
            carritoList.add(item)
        }

        fun eliminarPedido(pedido: Carrito) {
            carritoList.remove(pedido)
        }

        fun limpiarPedidos() {
            carritoList.clear()
        }

        fun total(): Int {
            var total = 0

            for (pedidos in carritoList) {
                total += pedidos.precio
            }

            return total
        }


        fun tiempoEspera(): Int {
            var tiempo = 0

            for (pedidos in carritoList) {
                when (pedidos.comida) {
                    "Tacos" -> tiempo += (pedidos.cantidad * 4) / 2
                    "Tortas" -> tiempo += (pedidos.cantidad * 10) / 2
                    "Burritos" -> tiempo += (pedidos.cantidad * 12) / 2
                }
            }

            return tiempo
        }

    }
}