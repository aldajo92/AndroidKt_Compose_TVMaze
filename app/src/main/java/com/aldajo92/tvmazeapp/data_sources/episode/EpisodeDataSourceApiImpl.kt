package com.aldajo92.tvmazeapp.data_sources.episode

import com.aldajo92.tvmazeapp.domain.Episode
import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.mappers.toDomainModel
import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.handleBodyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class EpisodeDataSourceApiImpl(
    private val api: TvMazeApi
) : EpisodeDataSource {

    override fun getEpisode(id: String): Flow<Episode> = MutableStateFlow(Episode())

    override fun saveEpisode(show: Show) = Unit

    override fun getAllEpisodes(showId: String): Flow<List<Episode>> = flow {
        try {
            val result = api.getEpisodes(showId).handleBodyResponse().map { it.toDomainModel() }
            emit(result)
        } catch (e: Exception){

        }
    }

}
