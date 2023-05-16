package com.application.presentation.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {
    private val _viewState = MutableLiveData<SplashViewState>()
    val viewState: LiveData<SplashViewState> = _viewState

}

sealed class SplashViewState {
    sealed class InitialDownload : SplashViewState() {
        object Processing : InitialDownload()
        object Success : InitialDownload()
        data class Failure(val error: Throwable) : InitialDownload()
    }
}
