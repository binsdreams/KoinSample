package com.bins.domain.entity


data class TrendingDomainEntity(
    var id :Int? = 0,
    var author: String?,
    var avatar: String?,
    var url:  String?,
    var description : String?,
    var stars : Int?,
    var forks : Int?,
    var currentPeriodStars :Int?,
    var timeStamp :Long)