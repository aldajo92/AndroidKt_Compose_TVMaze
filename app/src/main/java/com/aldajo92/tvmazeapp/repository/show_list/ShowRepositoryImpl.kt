package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.data_sources.show.ShowDataSource
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.exceptions.TVMazeAppException
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ShowRepositoryImpl(
    private val showDataSource: ShowDataSource
) : ShowRepository {

    private val showListFlow = MutableStateFlow<ShowsRequestStatus>(ShowsRequestStatus.OnSuccess())

    private val currentShows = mutableListOf<Show>()

    private var showMaps: MutableMap<String, Show> = mutableMapOf()

    private var currentPageNumber: Int = 1

    override fun getCurrentShows(): List<Show> = currentShows

    override fun getShowsByPage(page: Int) {
        showListFlow.value = ShowsRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            showDataSource.getAllShows(page).collect { result ->
                showListFlow.value = try {
                    currentShows.addAll(result)
                    showMaps.putAll(result.associateBy { show -> show.id }.toMutableMap())
                    currentPageNumber = page
                    ShowsRequestStatus.OnSuccess(currentShows)
                } catch (e: TVMazeAppException) {
                    ShowsRequestStatus.OnError(e.message.orEmpty())
                }
            }
        }
    }

    override fun getCurrentPage() = currentPageNumber

    override fun getShowFromCache(showID: String): Show? = showMaps[showID]

    override fun getFlowData(): Flow<ShowsRequestStatus> = showListFlow

}
