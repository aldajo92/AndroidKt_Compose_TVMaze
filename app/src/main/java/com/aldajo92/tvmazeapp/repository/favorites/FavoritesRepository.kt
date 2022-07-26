package com.aldajo92.tvmazeapp.repository.favorites

import com.aldajo92.tvmazeapp.domain.Show
import com.aldajo92.tvmazeapp.presentation.events.ShowsRequestStatus
import com.aldajo92.tvmazeapp.repository.FlowData

interface FavoritesRepository : FlowData<ShowsRequestStatus> {

    fun removeFavoriteShow(showId: String)

    fun saveFavoriteShow(show : Show)

}
