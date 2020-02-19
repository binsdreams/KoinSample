package com.sentry.data.repository

import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import com.bins.datalayer.mapper.ResponseDataToDomainEntityMapper
import com.bins.datalayer.repository.TrendingDataStore
import com.bins.domain.entity.DataEntity
import com.sentry.data.api.TrendingRepoApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class TrendingRepositoryRemote(private val api: TrendingRepoApi) : TrendingDataStore {

    private var mapper =ResponseDataToDomainEntityMapper()

    override suspend fun getTrendingRepository(): ReceiveChannel<DataEntity<List<TrendingRepoDbEntity>>> {
        return GlobalScope.produce {
            try {
                val repoResponse = api.getTrendingRepository().await()
                send(DataEntity.SUCCESS(mapper.map(repoResponse)))
            } catch (e: Exception) {
                send(DataEntity.ERROR(""+e.message))
            }
        }
    }
}