package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    showRepository: ShowRepository
) : ViewModel() {

    val listShowLiveData: LiveData<List<ShowUIModel>> =
        showRepository
            .getFlowData()
            .map { it.map { dto -> dto.toUIModel() } }
            .asLiveData()

    init {
        showRepository.getShows()
    }

}
