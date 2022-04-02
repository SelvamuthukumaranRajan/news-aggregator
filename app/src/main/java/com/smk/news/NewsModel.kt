package com.smk.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.smk.news.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback

class NewsModel(application: Application) : AndroidViewModel(application) {
    var allNews = MutableLiveData<List<News>>()

    init {
        val apiInterface = ApiInterface.create().getNews(null)

        apiInterface.enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>?,
                response: retrofit2.Response<Response>?
            ) {
                if (response != null) {
                    val newsList = mutableListOf<News>()
                    for (struct in response.body()?.data?.children!!) {
                        struct.news?.let { newsList.add(it) }
                    }
                    allNews.value = newsList
                }
            }

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                Log.e("News", t.toString())
            }
        })
    }

    fun loadMore(id: String){
        val apiInterface = ApiInterface.create().getNews(id)

        apiInterface.enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>?,
                response: retrofit2.Response<Response>?
            ) {
                if (response != null) {
                    val newsList = mutableListOf<News>()
                    for (struct in response.body()?.data?.children!!) {
                        struct.news?.let { newsList.add(it) }
                    }
                    allNews.value = newsList
                }
            }

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                Log.e("News", t.toString())
            }
        })
    }

}