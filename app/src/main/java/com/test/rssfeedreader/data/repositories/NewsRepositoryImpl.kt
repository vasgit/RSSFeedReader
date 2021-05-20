package com.test.rssfeedreader.data.repositories

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.data.stores.NewsDataStore
import com.test.rssfeedreader.utils.model.RepositoryResult
import com.test.rssfeedreader.utils.providers.ConnectionProvider
import com.test.rssfeedreader.model.NewsItemModel

class NewsRepositoryImpl(
    private val serverDataStore: NewsDataStore,
    private val databaseDataStore: NewsDataStore,
    private val connectionProvider: ConnectionProvider
) : NewsRepository {


    @Synchronized
    override suspend fun save(rssXmlModel: RssXmlModel, url: String) {
        databaseDataStore.save(rssXmlModel, url)
    }


    @Synchronized
    override suspend fun update(newsItemModel: NewsItemModel): RepositoryResult<NewsItemModel> {
        val result = RepositoryResult<NewsItemModel>()
        result.databaseResult = databaseDataStore.update(newsItemModel)
        return result
    }


    @Synchronized
    override suspend fun deleteAll() {
        databaseDataStore.deleteAll()
    }


    @Synchronized
    override suspend fun getNewsList(parameters: NewsParameters): RepositoryResult<List<NewsItemModel>> {
        val result = RepositoryResult<List<NewsItemModel>>()

        val resultALL = RepositoryResult<RssXmlModel>()

        if(connectionProvider.isNetworkAvailable()) {
            resultALL.serverResult = serverDataStore.getRssNews(parameters.link)

            if(resultALL.isServerResultSuccessful()) {
                save(resultALL.getSuccessfulResultValue(), parameters.link)
            }
        }

        result.databaseResult = databaseDataStore.getNewsList(parameters)

        return result
    }


    @Synchronized
    override suspend fun getRssNews(url: String): RepositoryResult<RssXmlModel> {
        throw UnsupportedOperationException()
    }


}