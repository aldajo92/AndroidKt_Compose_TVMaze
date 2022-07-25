package com.aldajo92.tvmazeapp.repository.detail

import com.aldajo92.tvmazeapp.network.home.ShowDTO

interface ShowDetailRepository {

    fun getSelectShow(): ShowDTO

    fun saveSelectedShow(showDTO: ShowDTO)

}
