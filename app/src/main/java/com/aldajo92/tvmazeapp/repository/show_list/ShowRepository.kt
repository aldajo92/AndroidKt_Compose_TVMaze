package com.aldajo92.tvmazeapp.repository.show_list

import com.aldajo92.tvmazeapp.network.home.ShowDTO

interface ShowRepository {

    suspend fun getShows(): List<ShowDTO>?

    suspend fun getShowDetail(showID: String): ShowDTO?

}
