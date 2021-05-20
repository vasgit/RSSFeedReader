package com.test.rssfeedreader.data.stores

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.utils.model.Result
import com.test.rssfeedreader.utils.helpers.executeBackgroundOperation
import com.test.rssfeedreader.utils.helpers.performBackgroundOperation
import com.test.rssfeedreader.database.tables.NewsItemsTable
import com.test.rssfeedreader.model.NewsItemModel

class NewsDatabaseDataStore(
    private val newsItemsTable: NewsItemsTable
) : NewsDataStore {


    override suspend fun getRssNews(url: String): Result<RssXmlModel> {
        throw UnsupportedOperationException()
    }


    override suspend fun getNewsList(parameters: NewsParameters): Result<List<NewsItemModel>> {
        return performBackgroundOperation {
            newsItemsTable.getByLink(parameters.link, parameters.loadAllData)
        }
    }

    override suspend fun update(newsItemModel: NewsItemModel): Result<NewsItemModel> {
        return performBackgroundOperation {
            newsItemsTable.update(newsItemModel)
        }
    }


    override suspend fun save(rssXmlModel: RssXmlModel, url: String) {
        executeBackgroundOperation {
            rssXmlModel.rssItems.forEach {
                newsItemsTable.save(it, rssXmlModel.title, url)
            }
        }
    }


    override suspend fun deleteAll() {
        executeBackgroundOperation {
            newsItemsTable.deleteAll()
        }
    }


}