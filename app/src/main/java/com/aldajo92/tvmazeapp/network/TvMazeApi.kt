package com.aldajo92.tvmazeapp.network

import com.aldajo92.tvmazeapp.network.home.EpisodeDTO
import com.aldajo92.tvmazeapp.network.home.ShowDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {

    @GET("/schedule")
    suspend fun getCurrentSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): List<EpisodeDTO>

    @GET("/shows")
    suspend fun getShows(@Query("page") pageNumber: Int): List<ShowDTO>
}
