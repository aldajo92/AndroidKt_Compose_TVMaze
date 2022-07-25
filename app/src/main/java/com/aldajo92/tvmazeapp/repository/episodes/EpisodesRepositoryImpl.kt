package com.aldajo92.tvmazeapp.repository.episodes

import com.aldajo92.tvmazeapp.network.TvMazeApi
import com.aldajo92.tvmazeapp.network.handleBodyResponse
import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.presentation.events.EpisodesRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

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
            episodesListFlow.value = try {
                requestsEpisodesFromAPI(showId)
                    .let { EpisodesRequestStatus.OnSuccess(it) }
            } catch (e: Exception) {
                EpisodesRequestStatus.OnError(e.message.orEmpty())
            }
        }
    }

    override fun getSelectedEpisode(episodeId: String): EpisodeDTO? {
        return episodesMaps?.get(episodeId)
    }

    override fun getFlowData(): Flow<EpisodesRequestStatus> = episodesListFlow

    private suspend fun requestsEpisodesFromAPI(showId: String): List<EpisodeDTO> =
        api.getEpisodes(showId)
            .handleBodyResponse()
            .also {
                lastShowId = showId
                lastEpisodes = it
                episodesMaps = it.associateBy { episodeDTO -> episodeDTO.id }
            }
}
