package com.bins.datalayer.repository

import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import com.bins.domain.entity.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface TrendingDataStore {

    suspend fun getTrendingRepository():ReceiveChannel<DataEntity<List<TrendingRepoDbEntity>>>

    fun saveReadings(response: List<TrendingRepoDbEntity>?)

}