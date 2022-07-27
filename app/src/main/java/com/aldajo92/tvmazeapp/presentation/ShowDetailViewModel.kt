package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toDomainModel
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.episodes.EpisodesRepository
import com.aldajo92.tvmazeapp.repository.favorites.FavoritesRepository
import com.aldajo92.tvmazeapp.ui.models.EpisodeResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.EpisodeUIModel
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    showDetailRepository: ShowDetailRepository,
    episodesRepository: EpisodesRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _selectedShowLiveData = MutableLiveData<ShowUIModel?>()
    val selectedShowLiveData: LiveData<ShowUIModel?> = _selectedShowLiveData

    var currentEpisodesList: List<EpisodeUIModel> = listOf()

    private var _favoriteState : Boolean = false
    val favoriteState: Flow<Boolean> = favoritesRepository
        .getFlowData()
        .map { favoriteShowResultStatus ->
            favoriteShowResultStatus.toUIEvent().let { event ->
                if (event is ShowResultUIEvents.OnSuccess) {
                    _favoriteState = event.list.groupBy { it.id }.containsKey(selectedShowLiveData.value?.id)
                    _selectedShowLiveData.value = _selectedShowLiveData.value?.copy(isFavorite = _favoriteState)
                }
                _favoriteState
            }
        }

    val episodesEventLiveData: LiveData<EpisodeResultUIEvents> = episodesRepository
        .getFlowData()
        .map { resultStatus ->
            resultStatus.toUIEvent().also {
                if (it is EpisodeResultUIEvents.OnSuccess) {
                    currentEpisodesList = it.list
                }
            }
        }
        .asLiveData()

    fun markAsFavorite(showUIModel: ShowUIModel, isFavorite: Boolean) {
        viewModelScope.launch {
            if (!isFavorite) favoritesRepository.saveFavoriteShow(showUIModel.toDomainModel())
            else favoritesRepository.removeFavoriteShow(showUIModel.id)
        }
    }

    init {
        val selectedShow = showDetailRepository.getSelectShow().toUIModel()
        _selectedShowLiveData.value = selectedShow
        episodesRepository.getEpisodes(selectedShow.id)
    }

}
