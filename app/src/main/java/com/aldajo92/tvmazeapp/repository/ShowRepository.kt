package com.aldajo92.tvmazeapp.repository

import com.aldajo92.tvmazeapp.network.home.ShowDTO

interface ShowRepository {

    suspend fun getShows(): List<ShowDTO>

    suspend fun getShowDetail(): String

}
