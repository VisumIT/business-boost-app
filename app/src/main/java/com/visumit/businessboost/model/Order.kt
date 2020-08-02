package com.visumit.businessboost.model

data class Order (
     val clientId: Int,
     val dicountId: String? = "0",
     val items: List<Items>?
)