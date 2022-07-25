package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchShowsRepositoryImpl @Inject constructor(
    private val api: TvMazeApi
) : SearchShowsRepository {

    private val searchShowListFlow =
        MutableStateFlow<ShowsRequestStatus>(ShowsRequestStatus.OnSuccess())

    private var searchMapResult: MutableMap<String, ShowDTO> = mutableMapOf()

    override fun performSearchShow(keyword: String) {
        searchShowListFlow.value = ShowsRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            searchShowListFlow.value = api
                .searchShows(keyword)
                .also {
                    searchMapResult =
                        it.associate { searchResultShow -> searchResultShow.show.id to searchResultShow.show }
                            .toMutableMap()
                }
                .map { it.show }
                .let { ShowsRequestStatus.OnSuccess(it) }
        }
    }

    override fun getSelectedShow(showId: String): ShowDTO? = searchMapResult[showId]

    override fun clearResults() {
        searchShowListFlow.value = ShowsRequestStatus.OnSuccess()
    }

    override fun getFlowData(): Flow<ShowsRequestStatus> = searchShowListFlow
}
