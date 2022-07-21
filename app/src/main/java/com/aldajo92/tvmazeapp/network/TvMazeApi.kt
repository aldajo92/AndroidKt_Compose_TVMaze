package com.aldajo92.tvmazeapp.network

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import com.aldajo92.tvmazeapp.network.home.ShowDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {

    @GET("/schedule")
    suspend fun getCurrentSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): List<EpisodeDTO>

    @GET("/shows")
    suspend fun getShows(@Query("page") pageNumber: Int): List<ShowDTO>

    @GET("/shows/{show_id}")
    suspend fun getShowDetail(@Path("show_id") showId: String): ShowDTO
}
