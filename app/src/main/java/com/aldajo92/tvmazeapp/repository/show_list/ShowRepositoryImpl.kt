package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ShowRepositoryImpl(
    private val api: TvMazeApi
) : ShowRepository {

    private val showListFlow = MutableStateFlow<ShowRequestStatus>(ShowRequestStatus.OnSuccess())

    private val currentShows = mutableListOf<ShowDTO>()

    private var showMaps: MutableMap<String, ShowDTO> = mutableMapOf()

    private var currentPageNumber: Int = 1

    override fun getCurrentShows() : List<ShowDTO> = currentShows

    override fun getShowsByPage(page: Int) {
        showListFlow.value = ShowRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            val result = api.getShows(page)
            currentPageNumber = page // TODO: Save current page if result is success
            currentShows.addAll(result)
            showListFlow.value = ShowRequestStatus.OnSuccess(currentShows)
            showMaps.putAll(result.associateBy { showDTO -> showDTO.id }.toMutableMap())
        }
    }

    override fun getCurrentPage() = currentPageNumber

    // TODO: Move to DetailShow Repository
    override fun saveSelectedShow(showDTO: ShowDTO) {
        showMaps[showDTO.id] = showDTO
    }

    override suspend fun getShowFromCache(showID: String): ShowDTO? = showMaps[showID]

    override fun getFlowData(): Flow<ShowRequestStatus> = showListFlow
}
