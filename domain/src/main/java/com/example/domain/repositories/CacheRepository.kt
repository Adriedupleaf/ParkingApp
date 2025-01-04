package com.example.domain.repositories
interface CacheRepository {
    fun saveAppVersion(appVersion:String?)
    fun saveLanguage(language:String?)
    fun getIsLoggedIn(): Boolean
}