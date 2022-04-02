package com.smk.news

import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("kind") var kind: String? = null,
    @SerializedName("data") var data: Data? = Data()

)

data class Data(

    @SerializedName("after") var after: String? = null,
    @SerializedName("children") var children: ArrayList<Struct> = arrayListOf()

)

data class Struct(

    @SerializedName("kind") var kind: String? = null,
    @SerializedName("data") var news: News? = News()

)

data class News(

    @SerializedName("ups") var ups: Int? = null,
    @SerializedName("selftext") var selftext: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("created") var created: Int? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null,
    @SerializedName("name") var name: String? = null,

)
