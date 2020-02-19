package com.sentry.data.repository

import com.bins.datalayer.mapper.DataToDomainMapper
import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.TrendingDomainEntity
import com.bins.domain.repository.TrendingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

class TrendingRepositoryImpl(private val remote: TrendingRepositoryRemote,
                             private val cache: TrendingRepositoryCache) : TrendingRepository {

    private var domainMapper = DataToDomainMapper()


    override suspend fun getTrendingRepository(): ReceiveChannel<DataEntity<List<TrendingDomainEntity>>> {
        return GlobalScope.produce {

            launch {
                val trendingRepository =
                    domainMapper.mapCacheToDomain(cache.getTrendingRepository().receive())
                send(DataEntity.SUCCESS(trendingRepository))
            }

            launch {
                when (val remoteNews = remote.getTrendingRepository().receive()) {
                    is DataEntity.SUCCESS -> {
                        cache.saveReadings(remoteNews.data)
                        send(DataEntity.SUCCESS(domainMapper.mapCacheToDomain(remoteNews)))
                    }
                    is DataEntity.ERROR -> {
                        send(DataEntity.ERROR(""+remoteNews.error))
                    }
                }
            }
        }
    }
}