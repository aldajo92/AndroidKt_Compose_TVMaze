package com.aldajo92.tvmazeapp.data_sources.favorite_shows

import com.aldajo92.tvmazeapp.domain.Show
import kotlinx.coroutines.flow.Flow

interface FavoriteShowsDataSource {

    fun getFavoriteShow(id: String) : Show

    fun saveFavoriteShow(show: Show)

    fun getAllFavoriteShows() : Flow<List<Show>>

}
