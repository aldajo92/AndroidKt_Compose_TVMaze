package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ShowRepositoryImpl(
    private val api: TvMazeApi
) : ShowRepository {

    private val showListFlow = MutableStateFlow<List<ShowDTO>>(emptyList())

    private var showMaps: MutableMap<String, ShowDTO> = mutableMapOf()

    private var currentPageNumber: Int = 1

    override fun getShows() {
        CoroutineScope(Dispatchers.IO).launch {
            showListFlow.value = api
                .getShows(1)
                .also {
                    currentPageNumber = 1
                    showMaps = it.associateBy { showDTO -> showDTO.id }.toMutableMap()
                }
        }
    }

    override fun getShowsByPage(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val currentShows = showListFlow.value.toMutableList()
            val result = api
                .getShows(page)
                .also {
                    currentPageNumber = page
                    showMaps.putAll(
                        it.associateBy { showDTO -> showDTO.id }.toMutableMap()
                    )
                }
            currentShows.addAll(result)
            showListFlow.value = currentShows
        }
    }

    override fun getCurrentPage() = currentPageNumber

    override fun saveSelectedShow(showDTO: ShowDTO) {
        showMaps[showDTO.id] = showDTO
    }

    override suspend fun getShowFromCache(showID: String): ShowDTO? = showMaps[showID]

    override fun getFlowData(): Flow<List<ShowDTO>> = showListFlow
}
