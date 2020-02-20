package com.bins.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bins.datalayer.repository.TrendingRepositoryCache
import com.bins.datalayer.repository.TrendingRepositoryRemote
import com.bins.domain.entity.DataEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class SyncWork(  appContext :Context,  workerParams :WorkerParameters) :CoroutineWorker(appContext,workerParams),KoinComponent{

    private val remote: TrendingRepositoryRemote by inject("remote")
    private val cache: TrendingRepositoryCache by inject("local")

    override suspend fun doWork(): Result = coroutineScope {
        launch {
            when (val remoteNews = remote.getTrendingRepository().receive()) {
                is DataEntity.SUCCESS -> {
                    cache.saveReadings(remoteNews.data)
                }
            }
        }
        Result.success()
    }

}