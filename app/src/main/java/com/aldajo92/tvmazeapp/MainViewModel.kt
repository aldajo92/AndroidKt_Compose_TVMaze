package com.aldajo92.tvmazeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _name = MutableLiveData<String>("Hola")
    val name: LiveData<String> = _name

    init {
        viewModelScope.launch {
            delay(5000)
            _name.value = "Hello"
        }
    }

}