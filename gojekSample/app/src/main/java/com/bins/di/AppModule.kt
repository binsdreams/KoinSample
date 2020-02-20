package com.bins.di

import androidx.room.Room
import com.bins.datalayer.api.TrendingRepoApi
import com.bins.datalayer.db.GitRepoDatabase
import com.bins.datalayer.repository.TrendingRepositoryCache
import com.bins.datalayer.repository.TrendingRepositoryImpl
import com.bins.datalayer.repository.TrendingRepositoryRemote
import com.bins.datalayer.util.NetworkUtil
import com.bins.domain.repository.TrendingRepository
import com.bins.domain.usecase.TrendingRepoUseCase
import com.bins.gojeksample.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { TrendingRepositoryRemote(api = get(TRENDING_REPO_API)) }
    single(name = "local") { TrendingRepositoryCache(database = get(DATABASE)) }
    single { TrendingRepositoryImpl(remote = get("remote"), cache = get("local"),
        networkState = NetworkUtil(androidApplication())) as TrendingRepository }
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
        MainViewModel(trendingRepoUseCase = get(TRENDING_REPO_USECASE),application = androidApplication())
    }

}

private const val BASE_URL = "https://github-trending-api.now.sh/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val TRENDING_REPO_API = "TRENDING_REPO_API"
private const val TRENDING_REPO_USECASE = "TRENDING_REPO_USECASE"
private const val DATABASE = "database"