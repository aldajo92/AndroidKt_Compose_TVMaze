package com.aldajo92.tvmazeapp.repository

import kotlinx.coroutines.flow.Flow

interface FlowData<T> {

    fun getFlowData() : Flow<T>

}