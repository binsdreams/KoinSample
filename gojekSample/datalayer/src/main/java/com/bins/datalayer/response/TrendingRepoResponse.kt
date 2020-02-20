package com.bins.datalayer.response

import com.google.gson.annotations.SerializedName

data class TrendingRepoResponse(
    @SerializedName("author") var author: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("avatar") var avatar: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("stars") var stars: Int? = 0,
    @SerializedName("forks") var forks: Int? = 0,
    @SerializedName("currentPeriodStars") var currentPeriodStars: Int = 0)

