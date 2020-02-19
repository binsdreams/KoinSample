package com.sentry.data.repository

import com.bins.datalayer.db.GitRepoDatabase
import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import com.bins.datalayer.repository.TrendingDataStore
import com.bins.domain.entity.DataEntity
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.reactive.openSubscription

class TrendingRepositoryCache(private val database: GitRepoDatabase) : TrendingDataStore {

    private var  trendingRepoDao = database.getTrendingRepoDao()

    override suspend fun getTrendingRepository(): ReceiveChannel<DataEntity<List<TrendingRepoDbEntity>>> {
        val mappedValue = trendingRepoDao.getAllRecords().map {
            DataEntity.SUCCESS(it)
        }
        return mappedValue.openSubscription()
    }


    fun saveReadings(response: List<TrendingRepoDbEntity>?) {
        response.let {  trendingRepoDao.deleteAll()
            trendingRepoDao.saveAllRecords(it?: emptyList())
        }
    }
}