package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.repository.FlowData
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus

interface SearchShowsRepository : FlowData<ShowsRequestStatus> {

    fun performSearchShow(keyword: String)

    fun getSelectedShow(showId: String): Show?

    fun clearResults()

}
