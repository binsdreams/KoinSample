package com.bins.gojeksample

import android.view.View
import androidx.databinding.BaseObservable
import com.bins.entity.TrendingData

class TrendingItemVM constructor(private var repoItem: TrendingData?) : BaseObservable() {

    fun getName(): String? {
       return repoItem?.author
    }

    fun getForks(): String? {
        return repoItem?.forks.toString()
    }

    fun getStars(): String? {
        return repoItem?.stars.toString()
    }

    fun getDescription(): String? {
        return repoItem?.description
    }

    fun getAvatar(): String? {
        return repoItem?.avatar
    }

    fun getUrl(): String? {
        var allSplits = repoItem?.url?.split("/")
        return allSplits?.last()
    }


    fun getLanguage(): String? {
        return "C++"
    }

    fun detailVisibility(): Int? {
        return View.GONE
    }

    fun onRowClick(): View.OnClickListener? {
        return View.OnClickListener {
            notifyChange()
        }
    }

}
