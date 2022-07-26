package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.repository.favorites.FavoritesRepository
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favoritesResultsLiveData: LiveData<ShowResultUIEvents> = favoritesRepository
        .getFlowData()
        .map { searchResultStatus ->
            searchResultStatus.toUIEvent()
        }
        .asLiveData()

    fun deleteShowFromFavorites(showId: String){
        favoritesRepository.removeFavoriteShow(showId)
    }

}
