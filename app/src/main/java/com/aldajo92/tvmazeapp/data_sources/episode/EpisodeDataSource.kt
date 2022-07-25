package com.aldajo92.tvmazeapp.data_sources.episode

import com.aldajo92.tvmazeapp.domain.Episode
import com.aldajo92.tvmazeapp.domain.Show
import kotlinx.coroutines.flow.Flow

interface EpisodeDataSource {

    fun getEpisode(id: String): Flow<Episode>

    fun saveEpisode(show: Show)

    fun getAllEpisodes(showId: String): Flow<List<Episode>>

}
