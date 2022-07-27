package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import com.aldajo92.tvmazeapp.repository.FlowData

interface ShowRepository : FlowData<ShowsRequestStatus> {

    fun getCurrentShows() : List<Show>

    fun getShowsByPage(page: Int)

    fun getCurrentPage(): Int

    fun getShowFromCache(showId: String): Show?

}
