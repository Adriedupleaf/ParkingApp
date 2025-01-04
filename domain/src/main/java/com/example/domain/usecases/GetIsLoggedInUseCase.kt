package com.example.domain.usecases

import com.example.domain.repositories.CacheRepository
import javax.inject.Inject

class GetIsLoggedInUseCase @Inject constructor(
    private val cacheRepository: CacheRepository
) {
    operator fun invoke(): Boolean = cacheRepository.getIsLoggedIn()
}