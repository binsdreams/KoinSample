package  com.bins.datalayer.api

import com.bins.datalayer.response.TrendingRepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface TrendingRepoApi {

    @GET("repositories?")
    fun getTrendingRepository(): Deferred<List<TrendingRepoResponse>>

}