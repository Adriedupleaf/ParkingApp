package com.example.app3.ui.navigator

import com.example.app3.BaseViewModel
import com.example.app3.utils.UIErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigatorViewModel @Inject constructor(
    uiErrorHandler: UIErrorHandler
) : BaseViewModel(uiErrorHandler) {

}