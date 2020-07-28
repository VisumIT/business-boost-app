package com.visumit.businessboost.model

data class Carrinho (
    val id: Int? = null,
    val idProduct: Int? = null,
    val name: String? = null,
    var quantidade: Int? = null,
    val imgUrl: String? = null,
    var totalPrice: Double? = null
)