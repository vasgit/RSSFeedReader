package com.test.rssfeedreader.data.stores

import com.test.rssfeedreader.api.RssApi
import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.utils.model.Result
import com.test.rssfeedreader.utils.helpers.performBackgroundOperation
import com.test.rssfeedreader.model.NewsItemModel

class NewsServerDataStore(
    private val apiRss: RssApi
) : NewsDataStore {


    override suspend fun getRssNews(url: String): Result<RssXmlModel> {
        return performBackgroundOperation {
            apiRss.getNews(url)
        }
    }


    override suspend fun getNewsList(parameters: NewsParameters): Result<List<NewsItemModel>> {
        throw UnsupportedOperationException()
    }


    override suspend fun save(rssXmlModel: RssXmlModel, url: String) {
        throw UnsupportedOperationException()
    }


    override suspend fun deleteAll() {
        throw UnsupportedOperationException()
    }


    override suspend fun update(newsItemModel: NewsItemModel): Result<NewsItemModel> {
        throw UnsupportedOperationException()
    }


}