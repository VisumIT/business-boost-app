package com.visumit.businessboost.model

data class Carrinho (
    val id: Int? = null,
    val idProduct: Int,
    val name: String? = null,
    var quantidade: Int,
    val imgUrl: String? = null,
    var totalPrice: Double
)