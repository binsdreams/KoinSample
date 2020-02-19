package com.bins.gojeksample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bins.entity.Data
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private var trendingAdapter =TrendingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        recyclerViewData.layoutManager = LinearLayoutManager(this)
        trendingAdapter = TrendingAdapter()
        recyclerViewData.adapter = trendingAdapter
        recyclerViewData.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        trendingAdapter.updateList(mainViewModel.getEmptyListForShimmer())
        mainViewModel.getTrendingRepo()

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.getTrendingRepoDataList().observe(this, Observer {
            when (it) {
                is Data.ERROR -> {
                    var error = it.error
                }
                is Data.SUCCESS -> {
                    it.data?.let { list -> trendingAdapter?.updateList(list) }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
