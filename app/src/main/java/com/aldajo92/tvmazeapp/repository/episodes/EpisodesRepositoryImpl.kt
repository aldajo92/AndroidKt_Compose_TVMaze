package com.aldajo92.tvmazeapp.repository.episodes

import com.aldajo92.tvmazeapp.data_sources.episode.EpisodeDataSource
import com.aldajo92.tvmazeapp.domain.Episode
import com.aldajo92.tvmazeapp.exceptions.TVMazeAppException
import com.aldajo92.tvmazeapp.presentation.events.EpisodesRequestStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class EpisodesRepositoryImpl(
    private val episodeDataSource: EpisodeDataSource
) : EpisodesRepository {

    private val episodesListFlow =
        MutableStateFlow<EpisodesRequestStatus>(EpisodesRequestStatus.OnSuccess(emptyList()))

    private var episodesMaps: Map<String, Episode>? = null

    private var lastEpisodes: List<Episode> = listOf()
    private var lastShowId: String = ""

    override fun getEpisodes(showId: String) {
        episodesListFlow.value = EpisodesRequestStatus.OnLoading
        CoroutineScope(Dispatchers.IO).launch {
            episodeDataSource.getAllEpisodes(showId).collect { episodeList ->
                episodesListFlow.value = try {
                    lastShowId = showId
                    lastEpisodes = episodeList
                    episodesMaps = episodeList.associateBy { episodeDTO -> episodeDTO.id }
                    EpisodesRequestStatus.OnSuccess(episodeList)
                } catch (e: TVMazeAppException) {
                    EpisodesRequestStatus.OnError(e.message.orEmpty())
                }
            }
        }
    }

    override fun getSelectedEpisode(episodeId: String): Episode? {
        return episodesMaps?.get(episodeId)
    }

    override fun getFlowData(): Flow<EpisodesRequestStatus> = episodesListFlow

}
