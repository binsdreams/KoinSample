package com.bins.datalayer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bins.datalayer.dbtable.TrendingRepoDbEntity

@Database(entities = [TrendingRepoDbEntity::class], version = 1)
abstract class GitRepoDatabase : RoomDatabase(){
    abstract fun getTrendingRepoDao() : TrendingRepoDao
}