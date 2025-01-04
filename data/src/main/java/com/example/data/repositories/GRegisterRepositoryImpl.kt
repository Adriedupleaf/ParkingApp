package com.example.data.repositories

import com.example.data.datasources.GServiceDataStore
import com.example.domain.repositories.GRegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GRegisterRepositoryImpl @Inject internal constructor(
    private val gServiceDataStore: GServiceDataStore
): GRegisterRepository {

    override suspend fun createAccount(username: String, password: String) {
        gServiceDataStore.register(email = username, password = password)
    }

    override suspend fun sendEmailConfirmation() {
        TODO("Not yet implemented")
    }
}