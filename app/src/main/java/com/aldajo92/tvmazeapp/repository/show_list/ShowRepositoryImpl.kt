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

    private var showMaps: Map<String, ShowDTO>? = null

    override fun getShows() {
        CoroutineScope(Dispatchers.IO).launch {
            showListFlow.value = api
                .getShows(1)
                .also {
                    showMaps = it.associateBy { showDTO -> showDTO.id }
                }
        }
    }

    override suspend fun getShowDetail(showID: String): ShowDTO? =
        showMaps?.get(showID)

    override fun getFlowData(): Flow<List<ShowDTO>> = showListFlow
}
