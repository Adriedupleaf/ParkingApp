package com.example.domain.repositories

interface GAuthenticateRepository {
    suspend fun auth(email: String, password: String)
    suspend fun logout()


}