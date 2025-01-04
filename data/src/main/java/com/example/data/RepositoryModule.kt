package com.example.data

import com.example.data.repositories.CacheRepositoryImpl
import com.example.data.repositories.GAuthenticateRepositoryImpl
import com.example.data.repositories.GRegisterRepositoryImpl
import com.example.data.repositories.ParkingSpotsRepositoryImpl
import com.example.domain.repositories.CacheRepository
import com.example.domain.repositories.GAuthenticateRepository
import com.example.domain.repositories.GRegisterRepository
import com.example.domain.repositories.ParkingSpotsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    internal fun providesGRegisterRepository(impl: GRegisterRepositoryImpl): GRegisterRepository = impl
    @Provides
    internal fun providesGAuthenticateRepository(impl: GAuthenticateRepositoryImpl): GAuthenticateRepository = impl
    @Provides
    internal fun providesCacheRepository(impl: CacheRepositoryImpl): CacheRepository = impl
    @Provides
    internal fun providesParkingSpots(impl: ParkingSpotsRepositoryImpl): ParkingSpotsRepository = impl
}