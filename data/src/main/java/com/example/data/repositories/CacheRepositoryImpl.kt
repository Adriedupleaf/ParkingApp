package com.example.data.repositories

import com.example.data.datasources.CacheDataSource
import com.example.domain.repositories.CacheRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class CacheRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource
) : CacheRepository {
    override fun saveAppVersion(appVersion: String?) {
        TODO("Not yet implemented")
    }

    override fun saveLanguage(language: String?) {
        TODO("Not yet implemented")
    }


    override fun getIsLoggedIn(): Boolean = cacheDataSource.getIsLoggedIn()


}