package com.bins.gojeksample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bins.entity.TrendingData
import com.bins.gojeksample.databinding.AuthorItemBinding

class TrendingAdapter : RecyclerView.Adapter<DataViewHolder>() {

    private var dataList = mutableListOf<TrendingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val dataItemBinding: AuthorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.author_item,
            parent, false
        )
        return DataViewHolder(dataItemBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.count()

    fun updateList(list: List<TrendingData>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}

class DataViewHolder(private var binding: AuthorItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dataItem: TrendingData) {
        binding.trendingRepo = TrendingItemVM(dataItem)
    }
}