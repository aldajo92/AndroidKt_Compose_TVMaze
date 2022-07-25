package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.presentation.ShowRequestStatus
import com.aldajo92.tvmazeapp.repository.FlowData

interface ShowRepository : FlowData<ShowRequestStatus> {

    fun getCurrentShows() : List<ShowDTO>

    fun getShowsByPage(page: Int)

    fun getCurrentPage(): Int

    fun getShowFromCache(showID: String): ShowDTO?

}
