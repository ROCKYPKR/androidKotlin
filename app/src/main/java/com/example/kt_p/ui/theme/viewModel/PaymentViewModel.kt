package com.example.kt_p.ui.theme.viewModel

import Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class PaymentViewModel (val respository:Repository ): ViewModel(){
    private val _uiState = MutableStateFlow(LoadingState.Success(emptyList()))
    val state: StateFlow<LoadingState> = _uiState

    sealed class LoadingState{
        data class  Success( val payment:List<String>):LoadingState()
        data class  Error(val exception: Throws):LoadingState()

    }
    fun launch(){
        viewModelScope.launch{
            respository.processPayment(34.0)
        }
    }


}