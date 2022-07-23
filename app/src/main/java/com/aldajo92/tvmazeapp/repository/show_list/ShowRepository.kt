package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.repository.FlowData

interface ShowRepository : FlowData<List<ShowDTO>> {

    fun getShows()

    suspend fun getShowDetail(showID: String): ShowDTO?

}
