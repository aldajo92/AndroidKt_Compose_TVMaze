package com.aldajo92.tvmazeapp.repository

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.ShowDTO

class ShowRepositoryImpl(private val api: TvMazeApi) : ShowRepository {
    override suspend fun getShows(): List<ShowDTO> {
        return api.getShows(1)
    }

    override suspend fun getShowDetail(): String = ""
}
