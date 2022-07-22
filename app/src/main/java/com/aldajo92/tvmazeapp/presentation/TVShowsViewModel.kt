package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    showRepository: ShowRepository
) : ViewModel() {

    val listShowData: LiveData<List<ShowDTO>> = showRepository.getFlowData().asLiveData()

    init {
        showRepository.getShows()
    }

}
