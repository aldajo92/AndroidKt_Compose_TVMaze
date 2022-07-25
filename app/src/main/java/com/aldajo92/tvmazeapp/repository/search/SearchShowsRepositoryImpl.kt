package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchShowsRepositoryImpl @Inject constructor(
    private val api: TvMazeApi
) : SearchShowsRepository {

    private val episodesListFlow =
        MutableStateFlow<ShowRequestStatus>(ShowRequestStatus.OnSuccess())

    private var searchMapResult: MutableMap<String, ShowDTO> = mutableMapOf()

    override fun performSearchShow(keyword: String) {
        episodesListFlow.value = ShowRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            episodesListFlow.value = api
                .searchShows(keyword)
                .also {
                    searchMapResult =
                        it.associate { searchResultShow -> searchResultShow.show.id to searchResultShow.show }
                            .toMutableMap()
                }
                .map { it.show }
                .let { ShowRequestStatus.OnSuccess(it) }
        }
    }

    override fun getSelectedShow(showId: String): ShowDTO? = searchMapResult[showId]

    override fun clearResults() {
        episodesListFlow.value = ShowRequestStatus.OnSuccess()
    }

    override fun getFlowData(): Flow<ShowRequestStatus> = episodesListFlow
}
