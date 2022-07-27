package com.aldajo92.tvmazeapp.repository.detail

import com.aldajo92.tvmazeapp.domain.Show

interface ShowDetailRepository {

    fun getSelectShow(): Show

    fun saveSelectedShow(show: Show)

}
