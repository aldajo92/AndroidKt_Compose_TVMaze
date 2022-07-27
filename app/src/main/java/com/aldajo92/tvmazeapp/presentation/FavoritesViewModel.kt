package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.mappers.toDomainModel
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.repository.detail.ShowDetailRepository
import com.aldajo92.tvmazeapp.repository.favorites.FavoritesRepository
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val showDetailRepository: ShowDetailRepository
) : ViewModel() {

    val favoritesResultsLiveData: LiveData<ShowResultUIEvents> = favoritesRepository
        .getFlowData()
        .map { searchResultStatus ->
            searchResultStatus.toUIEvent()
        }
        .asLiveData()

    fun deleteShowFromFavorites(showId: String) {
        favoritesRepository.removeFavoriteShow(showId)
    }

    fun saveSelectedShow(showUIModel: ShowUIModel) {
        showDetailRepository.saveSelectedShow(showUIModel.toDomainModel())
    }

}
