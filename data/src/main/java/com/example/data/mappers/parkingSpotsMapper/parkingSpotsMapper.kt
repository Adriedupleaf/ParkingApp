package com.example.data.mappers.parkingSpotsMapper

import com.example.data.models.ParkingSpot
import com.example.domain.models.CDParkingSpot

fun List<ParkingSpot>.toDomain(): List<CDParkingSpot> = map {
    CDParkingSpot(
        id = it.id,
        name = it.name,
        type = it.type,
        availability = it.availability,
        distance = it.distance,
        priceWeekday1 = it.priceWeekday1,
        priceWeekday2 = it.priceWeekday2,
        priceWeekday3 = it.priceWeekday3,
        priceWeekend1 = it.priceWeekend1,
        priceWeekend2 = it.priceWeekend2,
        priceWeekend3 = it.priceWeekend3,
        lat = it.lat,
        lng = it.lng
        )
}
