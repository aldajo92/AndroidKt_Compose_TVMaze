package com.aldajo92.tvmazeapp.repository.show_list

import kotlinx.coroutines.flow.Flow

interface FlowData<T> {

    fun getFlowData() : Flow<T>

}