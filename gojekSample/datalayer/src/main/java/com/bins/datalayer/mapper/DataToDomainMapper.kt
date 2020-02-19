package com.sentry.data.mappers

import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.TrendingDomainEntity

class DataToDomainMapper {

    fun mapCacheToDomain(response: DataEntity<List<TrendingRepoDbEntity>>): List<TrendingDomainEntity>? {
        return when (response) {
            is DataEntity.SUCCESS<List<TrendingRepoDbEntity>> ->
                response.data?.map { mapToDomainEntity(it) }
            is DataEntity.ERROR<List<TrendingRepoDbEntity>> ->
                response.data?.map { mapToDomainEntity(it) }

        }
    }

    private fun mapToDomainEntity(response: TrendingRepoDbEntity): TrendingDomainEntity = TrendingDomainEntity(
        id = response.id,
        author= response.author,
        avatar= response.avatar,
        url= response.url,
        description= response.description,
        stars= response.stars,
        forks= response.forks,
        currentPeriodStars= response.currentPeriodStars,
        timeStamp = System.currentTimeMillis()
    )
}
