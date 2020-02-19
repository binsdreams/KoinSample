package com.bins.domain.repository

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.TrendingDomainEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface TrendingRepository {

    suspend fun getTrendingRepository(): ReceiveChannel<DataEntity<List<TrendingDomainEntity>>>

}