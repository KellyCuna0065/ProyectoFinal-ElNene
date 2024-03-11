package com.example.proyectofinal.modelo_carrito

import androidx.annotation.DrawableRes

data class Carrito(
    val comida: String,
    val precio: Int,
    var cantidad: Int,
    @DrawableRes val img: Int
)
