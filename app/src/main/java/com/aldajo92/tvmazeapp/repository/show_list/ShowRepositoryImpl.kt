package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.exceptions.TVMazeAppException
import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.handleBodyResponse
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ShowRepositoryImpl(
    private val api: TvMazeApi
) : ShowRepository {

    private val showListFlow = MutableStateFlow<ShowsRequestStatus>(ShowsRequestStatus.OnSuccess())

    private val currentShows = mutableListOf<ShowDTO>()

    private var showMaps: MutableMap<String, ShowDTO> = mutableMapOf()

    private var currentPageNumber: Int = 1

    override fun getCurrentShows(): List<ShowDTO> = currentShows

    override fun getShowsByPage(page: Int) {
        showListFlow.value = ShowsRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            showListFlow.value = try {
                val result = requestShowsFromAPI(page)
                currentShows.addAll(result)
                showMaps.putAll(result.associateBy { showDTO -> showDTO.id }.toMutableMap())
                currentPageNumber = page
                ShowsRequestStatus.OnSuccess(currentShows)
            } catch (e: Exception) {
                ShowsRequestStatus.OnError(e.message.orEmpty())
            }
        }
    }

    override fun getCurrentPage() = currentPageNumber

    override fun getShowFromCache(showID: String): ShowDTO? = showMaps[showID]

    override fun getFlowData(): Flow<ShowsRequestStatus> = showListFlow

    private suspend fun requestShowsFromAPI(page: Int): List<ShowDTO> =
        api.getShows(page).handleBodyResponse()
}
