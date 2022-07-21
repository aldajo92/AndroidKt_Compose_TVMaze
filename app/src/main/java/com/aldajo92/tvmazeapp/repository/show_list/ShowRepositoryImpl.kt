package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO

class ShowRepositoryImpl(
    private val api: TvMazeApi
) : ShowRepository {

    private var showMaps: Map<String, ShowDTO>? = null

    override suspend fun getShows(): List<ShowDTO> =
        api.getShows(1)
            .also {
            showMaps = it.associateBy { showDTO -> showDTO.id }
        }

    override suspend fun getShowDetail(showID: String): ShowDTO? =
        showMaps?.get(showID)
}
