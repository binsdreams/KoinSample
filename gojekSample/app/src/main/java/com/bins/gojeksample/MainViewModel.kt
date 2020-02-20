package com.bins.gojeksample

import androidx.lifecycle.MutableLiveData
import com.bins.common.BaseViewModel
import com.bins.domain.usecase.TrendingRepoUseCase
import com.bins.entity.Data
import com.bins.entity.TrendingData
import com.bins.mapper.TrendingRepoDomainToPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(private val trendingRepoUseCase: TrendingRepoUseCase): BaseViewModel() {
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
}