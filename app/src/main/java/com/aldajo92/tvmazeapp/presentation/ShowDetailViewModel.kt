package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.show_episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.ui.models.EpisodeResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    showDetailRepository: ShowDetailRepository,
    episodesRepository: EpisodesRepository
) : ViewModel() {

    var currentEpisodesList: List<EpisodeUIModel> = listOf()

    private val _selectedShowLiveData = MutableLiveData<ShowUIModel?>()
    val selectedShowLiveData: LiveData<ShowUIModel?> = _selectedShowLiveData

    val episodesEventLiveData: LiveData<EpisodeResultUIEvents> = episodesRepository
        .getFlowData()
        .map { resultStatus ->
            resultStatus.toUIEvent().also {
                if (it is EpisodeResultUIEvents.OnSuccess) {
                    currentEpisodesList = it.list
                    Timber.d(("Current showList size = ${currentEpisodesList.size}").toString())
                }
            }
        }
        .asLiveData()

    init {
        val selectedShow = showDetailRepository.getSelectShow().toUIModel()
        _selectedShowLiveData.value = selectedShow
        episodesRepository.getEpisodes(selectedShow.id)
    }

}
