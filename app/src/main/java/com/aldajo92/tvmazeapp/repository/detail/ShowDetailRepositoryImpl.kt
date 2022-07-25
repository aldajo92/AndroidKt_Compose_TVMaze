package com.aldajo92.tvmazeapp.repository.detail

import com.aldajo92.tvmazeapp.network.home.ShowDTO

class ShowDetailRepositoryImpl : ShowDetailRepository {

    private lateinit var selectedShowDTO: ShowDTO

    override fun getSelectShow(): ShowDTO = selectedShowDTO

    override fun saveSelectedShow(showDTO: ShowDTO) {
        selectedShowDTO = showDTO
    }

}
