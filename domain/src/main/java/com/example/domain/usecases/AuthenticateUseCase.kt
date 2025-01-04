package com.example.domain.usecases

import com.example.domain.repositories.GAuthenticateRepository
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(
    private val gAuthenticateRepository: GAuthenticateRepository){
    suspend operator fun invoke(username: String, password: String) = gAuthenticateRepository.auth(username, password)
}