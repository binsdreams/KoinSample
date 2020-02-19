package com.bins.datalayer.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bins.datalayer.dbtable.TrendingRepoDbEntity
import io.reactivex.Flowable


@Dao
interface TrendingRepoDao {

    @Query("Select * from trendingRepoDbEntity")
    fun getAllRecords(): Flowable<List<TrendingRepoDbEntity>>

    @Insert
    fun saveAllRecords(treadingRepoList: List<TrendingRepoDbEntity>) : List<Long>

    @Delete
    fun delete(appSetting: TrendingRepoDbEntity)

    @Query("Delete from trendingRepoDbEntity")
    fun deleteAll()
}
