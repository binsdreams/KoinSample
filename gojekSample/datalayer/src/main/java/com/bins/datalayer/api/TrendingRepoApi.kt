package com.sentry.data.api

import com.sentry.data.entities.TrendingRepoResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TrendingRepoApi {

    @GET("/repositories?")
    fun getAllTrendingRepos(): Single<List<TrendingRepoResponse>>

}