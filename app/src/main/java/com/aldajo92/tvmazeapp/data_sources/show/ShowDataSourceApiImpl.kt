package com.aldajo92.tvmazeapp.data_sources.show

import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.mappers.toDomainModel
import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.handleBodyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowDataSourceApiImpl(
    private val api: TvMazeApi
) : ShowDataSource {

    override fun getShow(id: String): Flow<Show> = flow {
        val response = api.getShowDetail(id)
            .handleBodyResponse()
            .toDomainModel()
        emit(response)
    }

    override fun saveShow(show: Show) = Unit

    override fun getAllShows(page: Int): Flow<List<Show>> = flow {
        try {
            val list = api.getShows(page).handleBodyResponse().map { it.toDomainModel() }
            emit(list)
        } catch (e: Exception) {

        }
    }

    override fun searchShows(keyword: String): Flow<List<Show>> = flow {
        try {
            val list = api.searchShows(keyword).handleBodyResponse().map { it.show.toDomainModel() }
            emit(list)
        } catch (e: Exception) {

        }
    }

}
