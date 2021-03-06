package  com.bins.datalayer.mapper

import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import com.bins.datalayer.response.TrendingRepoResponse

class ResponseDataToDomainEntityMapper {

    fun map(response: List<TrendingRepoResponse>?) :List<TrendingRepoDbEntity>  =mapToDbEntityList(response)

    private fun mapToDbEntityList(responses: List<TrendingRepoResponse>?)
            : List<TrendingRepoDbEntity> = responses?.map { mapToDbEntity(it) } ?: emptyList()

    private fun mapToDbEntity(response: TrendingRepoResponse): TrendingRepoDbEntity = TrendingRepoDbEntity(
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
