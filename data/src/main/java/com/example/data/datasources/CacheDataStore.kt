package com.example.data.datasources

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CacheDataSource @Inject constructor() {
    var appVersion: String? = null
    private set
    var language: String? = "en"
    private set
    var username: String? = null
    private set
    var user: FirebaseUser? = null
        private set
    var accessToken: String? = null
        private set

    fun saveAppVersion(appVersion:String?) {
        this.appVersion = appVersion
    }
    fun saveLanguage(language:String?) {
        this.language = language
    }
    fun saveUsername(username:String?) {
        this.username = username
    }
    fun saveUser(user:FirebaseUser?){
        this.user = user
    }
    fun saveAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }
    fun getIsLoggedIn(): Boolean {
        return !this.accessToken.isNullOrEmpty()
    }
}
