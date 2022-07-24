package com.aldajo92.tvmazeapp.repository.search

import com.aldajo92.tvmazeapp.network.home.SearchResultDTO
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.repository.FlowData

interface SearchShowsRepository : FlowData<List<SearchResultDTO>>{

    fun performSearchShow(keyword : String)

    fun getSelectedShow(showId: String): ShowDTO?

}