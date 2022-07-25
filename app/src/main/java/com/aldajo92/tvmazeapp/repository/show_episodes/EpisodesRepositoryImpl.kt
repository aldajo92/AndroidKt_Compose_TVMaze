package com.aldajo92.tvmazeapp.repository.show_episodes

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.presentation.events.EpisodesRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EpisodesRepositoryImpl(
    private val api: TvMazeApi
) : EpisodesRepository {

    private val episodesListFlow =
        MutableStateFlow<EpisodesRequestStatus>(EpisodesRequestStatus.OnSuccess(emptyList()))

    private var episodesMaps: Map<String, EpisodeDTO>? = null

    private var lastEpisodes: List<EpisodeDTO> = listOf()
    private var lastShowId: String = ""

    override fun getEpisodes(showId: String) {
        episodesListFlow.value = EpisodesRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            if (showId != lastShowId) {
            episodesListFlow.value = api
                .getEpisodes(showId)
                .also {
                    lastShowId = showId
                    lastEpisodes = it
                    episodesMaps = it.associateBy { episodeDTO -> episodeDTO.id }
                }
                .let { EpisodesRequestStatus.OnSuccess(it) }
            } else {
                EpisodesRequestStatus.OnSuccess(lastEpisodes)
            }
        }
    }

    override fun getSelectedEpisode(episodeId: String): EpisodeDTO? {
        return episodesMaps?.get(episodeId)
    }

    override fun getFlowData(): Flow<EpisodesRequestStatus> = episodesListFlow
}
