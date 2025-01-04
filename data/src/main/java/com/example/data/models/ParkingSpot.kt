package com.example.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParkingSpot(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "availability")
    val availability: Int? = null,
    @Json(name = "distance")
    val distance: Int? = null,
    @Json(name = "priceWeekday1")
    val priceWeekday1: Int? = null,
    @Json(name = "priceWeekday2")
    val priceWeekday2: Int? = null,
    @Json(name = "priceWeekday3")
    val priceWeekday3: Int? = null,
    @Json(name = "priceWeekend1")
    val priceWeekend1: Int? = null,
    @Json(name = "priceWeekend2")
    val priceWeekend2: Int? = null,
    @Json(name = "priceWeekend3")
    val priceWeekend3: Int? = null,
    @Json(name = "lng")
    val lng: Double? = null,
    @Json(name = "lat")
    val lat: Double? = null,
    @Json(name = "id")
    val id: Long? = null,

    )

