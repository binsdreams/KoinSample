package com.bins.datalayer.db

import androidx.room.*
import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import io.reactivex.Flowable


@Dao
interface TrendingRepoDao {

    @Query("Select * from trendingRepoDbEntity")
    fun getAllRecords(): Flowable<List<TrendingRepoDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRecords(treadingRepoList: List<TrendingRepoDbEntity>) : List<Long>

    @Delete
    fun delete(appSetting: TrendingRepoDbEntity)

    @Query("Delete from trendingRepoDbEntity")
    fun deleteAll()
}
