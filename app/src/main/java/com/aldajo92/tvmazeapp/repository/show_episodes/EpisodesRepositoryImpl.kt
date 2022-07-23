package com.aldajo92.tvmazeapp.repository.show_episodes

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EpisodesRepositoryImpl(
    private val api: TvMazeApi
) : EpisodesRepository {

    private val episodesListFlow = MutableStateFlow<List<EpisodeDTO>>(emptyList())

    private var showMaps: Map<String, EpisodeDTO>? = null

    override fun getEpisodes(showId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            episodesListFlow.value = api
                .getEpisodes(showId)
                .also {
                    showMaps = it.associateBy { episodeDTO -> episodeDTO.id }
                }
        }
    }

    override fun getFlowData(): Flow<List<EpisodeDTO>> = episodesListFlow
}