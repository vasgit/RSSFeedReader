package com.test.rssfeedreader.api.services

import com.test.rssfeedreader.api.model.RssXmlModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {


    @GET
    fun getNews(@Url url: String): Call<RssXmlModel>


}