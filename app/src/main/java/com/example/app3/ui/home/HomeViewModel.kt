package com.example.app3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app3.BaseViewModel
import com.example.app3.utils.UIErrorHandler
import com.example.domain.models.CDParkingSpot
import com.example.domain.usecases.GetParkingSpotsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getParkingSpotsUseCase: GetParkingSpotsUseCase,
    uiErrorHandler: UIErrorHandler
) : BaseViewModel(uiErrorHandler) {

    private val mutableSpotsList = MutableLiveData<List<CDParkingSpot>>()
    val spotsList: LiveData<List<CDParkingSpot>> = mutableSpotsList

    fun getNearParkingSpots(radius: Int = 20){
        viewModelScope.launch {
            try {
                val result = getParkingSpotsUseCase.invoke(radius).sortedBy { it.distance }
                mutableSpotsList.postValue(result)
            } catch (e: Exception){
                handleError(e)
            }
        }

    }
    fun pay() {

    }
}