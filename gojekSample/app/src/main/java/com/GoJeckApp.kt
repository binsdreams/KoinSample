package com

import android.app.Application
import com.bins.di.*
import org.koin.android.ext.android.startKoin

class GoJeckApp : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(
                mRepositoryModules,
                mUseCaseModules,
                mLocalModules,
                mNetworkModules,
                mViewModels)
        )
    }
}