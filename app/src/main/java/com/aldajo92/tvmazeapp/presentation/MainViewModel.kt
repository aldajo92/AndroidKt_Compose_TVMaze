package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.repository.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val showRepository : ShowRepository
) : ViewModel() {

    private val _name = MutableLiveData<String>("Hola")
    val name: LiveData<String> = _name

    private val _listShowData = MutableLiveData<List<ShowDTO>>(listOf())
    val listShowData: LiveData<List<ShowDTO>> = _listShowData

    init {
        viewModelScope.launch {
            _listShowData.value = showRepository.getShows()
        }
    }

}
