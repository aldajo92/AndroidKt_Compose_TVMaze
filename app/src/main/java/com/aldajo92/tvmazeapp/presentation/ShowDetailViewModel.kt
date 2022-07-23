package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.show_episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val _selectedShowLiveData = MutableLiveData<ShowUIModel?>()
    val selectedShowLiveData: LiveData<ShowUIModel?> = _selectedShowLiveData

    val episodesLiveData: LiveData<List<EpisodeUIModel>?> =
        episodesRepository
            .getFlowData()
            .map { it?.map { dto -> dto.toUIModel() } }
            .asLiveData()

    fun getShowDetail(showID: String) {
        episodesRepository.getEpisodes(showID)
        if (showID.isNotBlank()) {
            viewModelScope.launch {
                _selectedShowLiveData.value = showRepository.getShowDetail(showID)?.toUIModel()
            }
        }
    }

}
