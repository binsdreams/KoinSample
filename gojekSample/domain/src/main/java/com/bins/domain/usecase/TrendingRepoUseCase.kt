package com.bins.domain.usecase

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.TrendingDomainEntity
import com.bins.domain.repository.TrendingRepository
import kotlinx.coroutines.channels.ReceiveChannel

class TrendingRepoUseCase(private val trendingRepository :TrendingRepository) {

    suspend fun execute(): ReceiveChannel<DataEntity<List<TrendingDomainEntity>>>{
        return trendingRepository.getTrendingRepository()
    }

}