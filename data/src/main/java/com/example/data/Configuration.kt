package com.example.data

sealed class Configuration {

    abstract val baseUrl: String

//    fun getPreLoginParamsToCache() = ConfigurationConstant.PRE_LOGIN_PARAMS_TO_CACHE


    data object Debug : Configuration() {
        override val baseUrl: String = "https://677448f292222241481913da.mockapi.io/"
    }

    data object Production : Configuration() {
        override val baseUrl: String = "https://677448f292222241481913da.mockapi.io/"
    }
}