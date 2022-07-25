package com.aldajo92.tvmazeapp.repository.episodes

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.presentation.events.EpisodesRequestStatus
import com.aldajo92.tvmazeapp.repository.FlowData

interface EpisodesRepository : FlowData<EpisodesRequestStatus> {

    fun getEpisodes(showId: String)

    fun getSelectedEpisode(episodeId: String) : EpisodeDTO?

}
