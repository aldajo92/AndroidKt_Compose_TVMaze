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

    private var episodesMaps: Map<String, EpisodeDTO>? = null

    private var lastEpisodes: List<EpisodeDTO> = listOf()
    private var lastShowId: String = ""

    override fun getEpisodes(showId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            if (showId != lastShowId) {
                episodesListFlow.value = listOf()
                episodesListFlow.value = api
                    .getEpisodes(showId)
                    .also {
                        lastShowId = showId
                        lastEpisodes = it
                        episodesMaps = it.associateBy { episodeDTO -> episodeDTO.id }
                    }
            }
        }
    }

    override fun getSelectedEpisode(episodeId: String): EpisodeDTO? {
        return episodesMaps?.get(episodeId)
    }

    override fun getFlowData(): Flow<List<EpisodeDTO>> = episodesListFlow
}
