package com.bins.gojeksample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bins.common.loadImage
import com.bins.entity.TrendingData
import com.bins.gojeksample.databinding.AuthorItemBinding
import com.facebook.shimmer.ShimmerFrameLayout

const val SHIMMER = 0
const val DATA = 1
class TrendingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<TrendingData?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(SHIMMER == viewType){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.shimmer_author_item,null)
            return ShimmerViewHolder(view)

        }else {
            val dataItemBinding: AuthorItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.author_item,
                parent, false
            )
            return DataViewHolder(dataItemBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is DataViewHolder){
            holder.bind(dataList[position])
        }else  if(holder is ShimmerViewHolder){
            holder.bind()
        }
    }

    override fun getItemCount(): Int = dataList.count()

    fun updateList(list: List<TrendingData?>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if(dataList[position] == null) SHIMMER else DATA
    }
}

class ShimmerViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(){
        (view as ShimmerFrameLayout).startShimmerAnimation()
    }
}

class DataViewHolder(private var binding: AuthorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dataItem: TrendingData?) {
        binding.trendingRepo = TrendingItemVM(dataItem)
        binding.avatarImage.loadImage(dataItem?.avatar?:"")
    }
}