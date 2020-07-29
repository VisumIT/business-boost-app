package com.visumit.businessboost.model

data class Order (
    private val representativeId: Int,
    private val clientId: Int,
    private val dicountId: String? = "",
    private val items: List<Items>
)