package com.example.data.repositories

import com.example.data.datasources.GServiceDataStore
import com.example.domain.exceptions.WarningException
import com.example.domain.exceptions.WrongCredentialsException
import com.example.domain.repositories.GAuthenticateRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GAuthenticateRepositoryImpl @Inject constructor(
    private val gServiceDataStore: GServiceDataStore
): GAuthenticateRepository {
    override suspend fun auth(email: String, password: String) {
        try {
            val response = gServiceDataStore.authenticate(email, password)
        } catch (e: Exception) {
            when(e){
                is FirebaseAuthInvalidCredentialsException -> throw WrongCredentialsException(e.message)
                else -> throw WarningException(e.message)
            }
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}