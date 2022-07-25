package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.repository.FlowData
import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus

interface SearchShowsRepository : FlowData<ShowRequestStatus> {

    fun performSearchShow(keyword: String)

    fun getSelectedShow(showId: String): ShowDTO?

    fun clearResults()

}
