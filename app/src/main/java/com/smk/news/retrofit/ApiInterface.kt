package com.smk.news.retrofit

import com.smk.news.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("hot.json?limit=10")
    fun getNews(@Query("after") after: String?) : Call<Response>

    companion object {

        var BASE_URL = "https://www.reddit.com/r/health/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}