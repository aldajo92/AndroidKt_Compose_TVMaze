package com.aldajo92.tvmazeapp.repository.show_episodes

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.repository.FlowData

interface EpisodesRepository : FlowData<List<EpisodeDTO>> {

    fun getEpisodes(showId: String)

}
