package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.home.ShowDTO

interface ShowRepository : FlowData<List<ShowDTO>> {

    fun getShows()

    suspend fun getShowDetail(showID: String): ShowDTO?

}
