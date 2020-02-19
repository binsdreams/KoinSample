package com.sentry.di

import androidx.room.Room
import com.bins.datalayer.db.GitRepoDatabase
import com.bins.domain.repository.TrendingRepository
import com.bins.domain.usecase.TrendingRepoUseCase
import com.bins.gojeksample.MainViewModel
import com.sentry.data.api.TrendingRepoApi
import com.sentry.data.repository.TrendingRepositoryCache
import com.sentry.data.repository.TrendingRepositoryImpl
import com.sentry.data.repository.TrendingRepositoryRemote
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { TrendingRepositoryRemote(api = get(TRENDING_REPO_API)) }
    single(name = "local") { TrendingRepositoryCache(database = get(DATABASE)) }
    single { TrendingRepositoryImpl(remote = get("remote"), cache = get("local")) as TrendingRepository }
}

val mUseCaseModules = module {
    factory(name = TRENDING_REPO_USECASE) {
        TrendingRepoUseCase(
            trendingRepository = get()
        )
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = TRENDING_REPO_API) { (get(RETROFIT_INSTANCE) as Retrofit).create(TrendingRepoApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            GitRepoDatabase::class.java,
            "GitRepoDatabase"
        ).build()
    }
}

val mViewModels = module {

    viewModel {
        MainViewModel(trendingRepoUseCase = get(TRENDING_REPO_USECASE))
    }

}

private const val BASE_URL = "https://github-trending-api.now.sh/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val TRENDING_REPO_API = "TRENDING_REPO_API"
private const val TRENDING_REPO_USECASE = "TRENDING_REPO_USECASE"
private const val DATABASE = "database"