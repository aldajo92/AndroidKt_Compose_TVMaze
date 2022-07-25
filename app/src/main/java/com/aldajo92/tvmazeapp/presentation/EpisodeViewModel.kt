package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.show_episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val _selectedEpisodeLiveData = MutableLiveData<EpisodeUIModel?>()
    val selectedEpisodeLiveData: LiveData<EpisodeUIModel?> = _selectedEpisodeLiveData

    fun getEpisodeDetails(episodeId: String) {
        viewModelScope.launch {
            if (episodeId.isNotBlank()) {
                val response = episodesRepository.getSelectedEpisode(episodeId)
                _selectedEpisodeLiveData.value = response?.toUIModel()

            }
        }
    }

}
