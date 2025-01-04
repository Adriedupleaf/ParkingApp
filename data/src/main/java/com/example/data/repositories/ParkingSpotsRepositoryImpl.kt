package com.example.data.repositories

import com.example.data.datasources.ParkingSpotsDataStore
import com.example.data.mappers.parkingSpotsMapper.toDomain
import com.example.domain.models.CDParkingSpot
import com.example.domain.repositories.ParkingSpotsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ParkingSpotsRepositoryImpl @Inject constructor(
    private val parkingSpotsDataStore: ParkingSpotsDataStore
) : ParkingSpotsRepository {

    override suspend fun getSpots(radius: Int): List<CDParkingSpot> {
        val response = parkingSpotsDataStore.getSpots(radius)

        return response.toDomain()
    }
}