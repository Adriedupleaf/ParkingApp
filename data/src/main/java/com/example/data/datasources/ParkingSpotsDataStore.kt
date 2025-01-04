package com.example.data.datasources

import com.example.data.api.ParkApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ParkingSpotsDataStore @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val parkApi: ParkApi

){
    suspend fun getSpots(radius: Int) = parkApi.getParkingSpots()
}