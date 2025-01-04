package com.example.domain.models

data class CDParkingSpot(
    val name: String? = null,
    val type: String? = null,
    val distance: Int? = null,
    val availability: Int? = null,
    val priceWeekday1: Int? = null,
    val priceWeekday2: Int? = null,
    val priceWeekday3: Int? = null,
    val priceWeekend1: Int? = null,
    val priceWeekend2: Int? = null,
    val priceWeekend3: Int? = null,
    val lng: Double? = null,
    val lat: Double? = null,
    val id: Long? = null,

)
