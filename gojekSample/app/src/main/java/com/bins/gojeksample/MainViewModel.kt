package com.bins.gojeksample

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import com.bins.common.BaseViewModel
import com.bins.domain.usecase.TrendingRepoUseCase
import com.bins.entity.Data
import com.bins.entity.TrendingData
import com.bins.mapper.TrendingRepoDomainToPresentationMapper
import com.bins.worker.SyncWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit


const val SYNC_DATA_WORK = "SYNC_DATA_WORK"
class MainViewModel(private val application : Context,private val trendingRepoUseCase: TrendingRepoUseCase): BaseViewModel() {

    private val mapper  =TrendingRepoDomainToPresentationMapper()
    var dataList = MutableLiveData<Data<List<TrendingData>>>()

    fun getTrendingRepo(foreRefresh :Boolean) {
        if(dataList.value != null && !foreRefresh) {
            dataList.postValue(dataList.value)
        }else{
            launch {
                val deviceInfo = trendingRepoUseCase.execute()
                deviceInfo.consumeEach { response ->
                    val mappedResponse = mapper.mapFrom(response)
                    withContext(Dispatchers.Main) {
                        dataList.postValue(mappedResponse)
                        schedule()
                    }
                }
            }
        }
    }

    fun getTrendingRepoDataList() = dataList

    fun getEmptyListForShimmer():List<TrendingData?>{
        val arrayList = ArrayList<TrendingData?>(10)
        for (i in 0..10){
            arrayList.add(null)
        }
        return arrayList
    }

    private fun schedule(){
        var  workManager = WorkManager.getInstance(application)
        var continuation: WorkContinuation = workManager
            .beginUniqueWork(
                SYNC_DATA_WORK,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(SyncWork::class.java)
            )
        val blurBuilder = OneTimeWorkRequest.Builder(SyncWork::class.java).setInitialDelay(2,TimeUnit.HOURS)
        continuation = continuation.then(blurBuilder.build())
        continuation.enqueue()
    }
}