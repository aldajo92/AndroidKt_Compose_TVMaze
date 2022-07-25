package com.aldajo92.tvmazeapp.network

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.network.home.SearchResultDTO
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface TvMazeApi {

    @GET("/shows")
    suspend fun getShows(@Query("page") pageNumber: Int): Response<List<ShowDTO>>

    @GET("/search/shows")
    suspend fun searchShows(@Query("q") keyword: String): Response<List<SearchResultDTO>>

    @GET("/shows/{show_id}")
    suspend fun getShowDetail(@Path("show_id") showId: String): Response<ShowDTO>

    @GET("/shows/{show_id}/episodes")
    suspend fun getEpisodes(@Path("show_id") showId: String): Response<List<EpisodeDTO>>
}
