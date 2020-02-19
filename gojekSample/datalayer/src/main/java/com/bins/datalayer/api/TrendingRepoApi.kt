package com.sentry.data.api

import com.sentry.data.entities.TrendingRepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface TrendingRepoApi {

    @GET("repositories?")
    fun getTrendingRepository(): Deferred<List<TrendingRepoResponse>>

}