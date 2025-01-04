package com.example.domain.repositories

import com.example.domain.models.CDParkingSpot

interface ParkingSpotsRepository {
    suspend fun getSpots(radius: Int): List<CDParkingSpot>
}