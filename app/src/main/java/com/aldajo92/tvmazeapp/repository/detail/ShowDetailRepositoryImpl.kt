package com.aldajo92.tvmazeapp.repository.detail

import com.aldajo92.tvmazeapp.domain.Show

class ShowDetailRepositoryImpl : ShowDetailRepository {

    private lateinit var selectedShow: Show

    override fun getSelectShow(): Show = selectedShow

    override fun saveSelectedShow(show: Show) {
        selectedShow = show
    }

}
