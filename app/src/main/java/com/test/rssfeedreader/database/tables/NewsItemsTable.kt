package com.test.rssfeedreader.database.tables

import com.test.rssfeedreader.utils.model.Result
import com.test.rssfeedreader.api.model.NewsItemXmlModel
import com.test.rssfeedreader.database.dao.RssItemsDao
import com.test.rssfeedreader.database.model.DatabaseRssItems
import com.test.rssfeedreader.database.tables.base.BaseTable
import com.test.rssfeedreader.mappings.*
import com.test.rssfeedreader.model.NewsItemModel

class NewsItemsTable(
    private val rssItemsDao: RssItemsDao
) : BaseTable() {


    @Synchronized
    fun save(profileInfo: NewsItemXmlModel, sourceTitle: String, sourceLink: String) {
        rssItemsDao.insertIgnore(profileInfo.mapToDatabaseRssItems(sourceTitle, sourceLink))
    }


    @Synchronized
    fun update(newsItemModel: NewsItemModel): Result<NewsItemModel> {
        rssItemsDao.update(newsItemModel.mapToDatabaseRssItems())
        return Result.Success(newsItemModel)
    }


    @Synchronized
    fun deleteAll() {
        rssItemsDao.deleteAll()
    }


    @Synchronized
    fun getByLink(link: String, loadBaned: Boolean): Result<List<NewsItemModel>> {
        return if (loadBaned) {
            rssItemsDao.getByLinkAllData(link).toResult(
                List<DatabaseRssItems>::mapToNewsItemModelList
            )
        } else {
            rssItemsDao.getByLink(link).toResult(
                List<DatabaseRssItems>::mapToNewsItemModelList
            )
        }
    }


}