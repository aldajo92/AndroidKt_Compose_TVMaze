package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toDomainModel
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.favorites.FavoritesRepository
import com.aldajo92.tvmazeapp.repository.search.SearchShowsRepository
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchShowsRepository: SearchShowsRepository,
    private val showDetailRepository: ShowDetailRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private var currentShowList: List<ShowUIModel> = listOf()
    private var favoritesShowMap: MutableMap<String, ShowUIModel> = mutableMapOf()

    private val showsEventFlow: Flow<ShowResultUIEvents> = searchShowsRepository
        .getFlowData()
        .map { showResultStatus ->
            showResultStatus.toUIEvent()
        }

    private val favoritesShowEventFlow: Flow<ShowResultUIEvents> = favoritesRepository
        .getFlowData()
        .map { favoriteShowResultStatus ->
            favoriteShowResultStatus.toUIEvent()
        }

    val loadingLiveData: LiveData<Boolean> =
        showsEventFlow.combine(favoritesShowEventFlow) { showEvent, favoriteShowEvent ->
            showEvent is ShowResultUIEvents.OnLoading || favoriteShowEvent is ShowResultUIEvents.OnLoading
        }.asLiveData()

    val showListLiveData =
        showsEventFlow.combine(favoritesShowEventFlow) { showEvent, favoriteShowEvent ->

            if (favoriteShowEvent is ShowResultUIEvents.OnSuccess) {
                favoritesShowMap = favoriteShowEvent.list.associateBy { it.id }.toMutableMap()
            }

            if (showEvent is ShowResultUIEvents.OnSuccess) {
                currentShowList = showEvent.list
            }

            currentShowList = currentShowList.map { show ->
                if (favoritesShowMap.containsKey(show.id)) show.copy(isFavorite = true)
                else show.copy(isFavorite = false)
            }

            currentShowList
        }

    init {
        searchShowsRepository.clearResults()
    }

    private val _searchTextState = MutableLiveData("")
    val searchTextState: LiveData<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun performSearch(keyword: String) {
        searchShowsRepository.performSearchShow(keyword)
    }

    fun saveSelectedShow(showUIModel: ShowUIModel) {
        showDetailRepository.saveSelectedShow(showUIModel.toDomainModel())
    }

    fun markAsFavorite(showUIModel: ShowUIModel, isFavorite: Boolean) {
        viewModelScope.launch {
            if (!isFavorite)
                favoritesRepository.saveFavoriteShow(showUIModel.toDomainModel())
            else favoritesRepository.removeFavoriteShow(showUIModel.id)
        }
    }

}
