package com.test.rssfeedreader.data.base

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.model.NewsItemModel

interface NewsData<
    NewsItemsListResult,
    RssNewsResult,
    UpdateResult
> {

    suspend fun getRssNews(url: String): RssNewsResult

    suspend fun getNewsList(parameters: NewsParameters): NewsItemsListResult

    suspend fun save(rssXmlModel: RssXmlModel, url: String)

    suspend fun update(newsItemModel: NewsItemModel): UpdateResult

    suspend fun deleteAll()

}