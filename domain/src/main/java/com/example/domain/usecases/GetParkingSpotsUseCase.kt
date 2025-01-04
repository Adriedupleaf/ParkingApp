package com.example.domain.usecases

import com.example.domain.repositories.ParkingSpotsRepository
import javax.inject.Inject

class GetParkingSpotsUseCase @Inject constructor(
    private val parkingSpotsRepository: ParkingSpotsRepository
){
    suspend fun invoke(radius: Int) = parkingSpotsRepository.getSpots(radius)
}