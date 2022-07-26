package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.favorites.FavoritesRepository
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val showRepository: ShowRepository,
    private val showDetailRepository: ShowDetailRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    var currentShowList: List<ShowUIModel> = listOf()

    val showEventsLiveData: LiveData<ShowResultUIEvents> = showRepository
        .getFlowData()
        .map { searchResultStatus ->
            searchResultStatus.toUIEvent().also {
                if (it is ShowResultUIEvents.OnSuccess) {
                    currentShowList = it.list
                }
            }
        }
        .asLiveData()


    init {
        makeFirstRequest()
    }

    fun makeFirstRequest() {
        val currentShows = showRepository.getCurrentShows()
        if (currentShows.isEmpty()) {
            showRepository.getShowsByPage(1)
        } else {
            currentShowList = currentShows.map { it.toUIModel() }
        }
    }

    fun loadNextShows() {
        val currentPage = showRepository.getCurrentPage()
        Timber.d((currentPage + 1).toString())
        showRepository.getShowsByPage(currentPage + 1)
    }

    fun saveSelectedShow(showId: String) {
        showRepository.getShowFromCache(showId)?.let {
            showDetailRepository.saveSelectedShow(it)
        }
    }

    fun markAsFavorite(showId: String){
        viewModelScope.launch {
            showRepository.getShowFromCache(showId)?.let {
                favoritesRepository.saveFavoriteShow(it)
            }
        }
    }

}
