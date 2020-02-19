package com.bins.mapper

import com.bins.domain.entity.Data
import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.TrendingData
import com.bins.domain.entity.TrendingDomainEntity

class TrendingRepoDomainToPresentationMapper {

    fun mapFrom(data: DataEntity<List<TrendingDomainEntity>>): Data<List<TrendingData>> {
        return when (data) {
            is DataEntity.SUCCESS -> Data.SUCCESS(data.data?.let { mapListToPresentation(it) })
            is DataEntity.ERROR -> Data.ERROR(error =  data.error)
        }
    }

    private fun mapListToPresentation(trendingList: List<TrendingDomainEntity>?)
            : List<TrendingData> = trendingList?.map { mapTrendingToPresentation(it) }
        ?: emptyList()

    private fun mapTrendingToPresentation(response: TrendingDomainEntity): TrendingData = TrendingData(
        id = response.id,
        author= response.author,
        avatar= response.avatar,
        url= response.url,
        description= response.description,
        stars= response.stars,
        forks= response.forks,
        currentPeriodStars= response.currentPeriodStars,
        timeStamp = response.timeStamp
    )

}