package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSource
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.exceptions.TVMazeAppException
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchShowsRepositoryImpl @Inject constructor(
    private val showDataSource: ShowDataSource
) : SearchShowsRepository {

    private val searchShowListFlow =
        MutableStateFlow<ShowsRequestStatus>(ShowsRequestStatus.OnSuccess())

    private var searchMapResult: MutableMap<String, Show> = mutableMapOf()

    override fun performSearchShow(keyword: String) {
        searchShowListFlow.value = ShowsRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            showDataSource.searchShows(keyword).collect { listShow ->
                searchShowListFlow.value = try {
                    searchMapResult = listShow.associateBy { show -> show.id }.toMutableMap()
                    ShowsRequestStatus.OnSuccess(listShow)
                } catch (e: TVMazeAppException) {
                    ShowsRequestStatus.OnError(e.message.orEmpty())
                }
            }
        }
    }

    override fun getSelectedShow(showId: String): Show? = searchMapResult[showId]

    override fun clearResults() {
        searchShowListFlow.value = ShowsRequestStatus.OnSuccess()
    }

    override fun getFlowData(): Flow<ShowsRequestStatus> = searchShowListFlow
}
