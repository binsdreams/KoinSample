package com.bins.datalayer.db

import androidx.room.*
import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface TrendingRepoDao {

    @Query("Select * from trendingRepoDbEntity")
    fun getAllRecords(): Flowable<TrendingRepoDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRecords(treadingRepoList: List<TrendingRepoDbEntity>) : Single<List<Long>>

    @Delete
    fun delete(appSetting: TrendingRepoDbEntity):Completable

    @Query("Delete from trendingRepoDbEntity")
    fun deleteAll(): Flowable<TrendingRepoDbEntity>
}
