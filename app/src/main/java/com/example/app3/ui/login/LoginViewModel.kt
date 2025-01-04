package com.example.app3.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.app3.BaseViewModel
import com.example.app3.utils.UIErrorHandler
import com.example.domain.usecases.AuthenticateUseCase
import com.example.domain.usecases.GetIsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getIsLoggedInUseCase: GetIsLoggedInUseCase,
    uiErrorHandler: UIErrorHandler
) : BaseViewModel(uiErrorHandler) {
    private var username: String = ""
    private var password: String = ""
    private val mutableIsLoggedIn = MediatorLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = mutableIsLoggedIn
    fun setUsername(username: String) {
        this.username = username
    }
    fun setPassword(password: String) {
    this.password = password
    }

    fun auth(){
        viewModelScope.launch {
            try {
                username = "adriedupleaf1@gmail.com"
                password = ""
                 authenticateUseCase(username, password)
            } catch(e: Exception){
                handleError(e)
            }
        }
    }
    fun isLoggedIn(){
        viewModelScope.launch {
            delay(1000)
            val resp = getIsLoggedInUseCase()
            mutableIsLoggedIn.postValue(resp)
        }
    }
}