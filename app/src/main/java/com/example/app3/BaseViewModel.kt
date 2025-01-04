package com.example.app3

import androidx.lifecycle.ViewModel
import com.example.app3.utils.UIErrorHandler
import java.lang.Exception

abstract class BaseViewModel(
    uiErrorHandler: UIErrorHandler
):
    UIErrorHandler by uiErrorHandler,
    ViewModel() {
fun formatToInt(value: String) : Int{
    return try {
        value.toInt()
    }    catch (_:Exception){
        0
    }
}
}