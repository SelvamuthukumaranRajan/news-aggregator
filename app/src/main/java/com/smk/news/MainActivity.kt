package com.smk.news

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsModel
    lateinit var newsRV: RecyclerView
    lateinit var btLoadMore: Button
    lateinit var scrollview: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsRV = findViewById(R.id.rv_news)

        newsRV.layoutManager = LinearLayoutManager(this)
        val newsRVAdapter = NewsAdapter(this)
        newsRV.adapter = newsRVAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NewsModel::class.java)

        viewModel.allNews.observe(this, Observer { list ->
            list?.let {
                newsRVAdapter.updateList(it)
            }
        })

        btLoadMore = findViewById(R.id.bt_loadMore)
        scrollview= findViewById(R.id.view_scrollview)
        btLoadMore.setOnClickListener {
            viewModel.allNews.value!![9].name?.let { it1 -> viewModel.loadMore(it1) }
            newsRV.smoothScrollToPosition(0)
            scrollview.fullScroll(View.FOCUS_UP)
        }

    }
}
