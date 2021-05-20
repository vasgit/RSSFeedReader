package com.test.rssfeedreader.api

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.api.services.RssService
import com.test.rssfeedreader.api.utils.ResponseExtractor
import com.test.rssfeedreader.utils.model.Result

class RssApi(
    private val rssService: RssService,
    private val responseExtractor: ResponseExtractor
) {


    fun getNews(url: String): Result<RssXmlModel> {
        return responseExtractor.extractNewsResponse(rssService.getNews(url))
    }


}