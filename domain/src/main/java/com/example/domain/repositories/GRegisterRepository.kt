package com.example.domain.repositories

interface GRegisterRepository {
    suspend fun createAccount(username: String, password: String)
    suspend fun sendEmailConfirmation()
}