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

    fun getTrendingRepo() {
        launch {
            val deviceInfo = trendingRepoUseCase.execute()
            deviceInfo.consumeEach {response ->
                val mappedResponse = mapper.mapFrom(response)
                withContext(Dispatchers.Main) {
                    dataList.postValue(mappedResponse)
                }
            }
        }
    }

    fun getTrendingRepoDataList() = dataList
}