package com.bins.datalayer.dbtable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trendingRepoDbEntity")
data class TrendingRepoDbEntity(
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var author: String,
    var avatar: String,
    var url:  String,
    var description : String,
    var stars : Int,
    var forks : Int,
    var currentPeriodStars :Int)