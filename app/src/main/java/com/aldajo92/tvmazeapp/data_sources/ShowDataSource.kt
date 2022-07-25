package com.aldajo92.tvmazeapp.data_sources

import com.aldajo92.tvmazeapp.domain.Show
import kotlinx.coroutines.flow.Flow

interface ShowDataSource {

    fun getShow(id: String): Flow<Show>

    fun saveShow(show: Show)

    fun getAllShows(page: Int): Flow<List<Show>>

    fun searchShows(keyword: String): Flow<List<Show>>

}
