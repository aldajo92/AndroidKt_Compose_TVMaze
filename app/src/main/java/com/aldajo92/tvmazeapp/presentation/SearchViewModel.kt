package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldajo92.tvmazeapp.mappers.toUIEvent
import com.aldajo92.tvmazeapp.repository.search.SearchShowsRepository
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.ui.models.ShowResultUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchShowsRepository: SearchShowsRepository,
    private val showRepository: ShowRepository
) : ViewModel() {

    init {
        searchShowsRepository.clearResults()
    }

    val searchResultsLiveData: LiveData<ShowResultUIEvents> = searchShowsRepository
        .getFlowData()
        .map { searchResultStatus ->
            searchResultStatus.toUIEvent()
        }
        .asLiveData()

    private val _searchTextState = MutableLiveData("")
    val searchTextState: LiveData<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun performSearch(keyword: String) {
        searchShowsRepository.performSearchShow(keyword)
    }

    fun setSelectedShow(showId: String) {
        searchShowsRepository.getSelectedShow(showId)?.let {
            showRepository.saveSelectedShow(it)
        }
    }

}
