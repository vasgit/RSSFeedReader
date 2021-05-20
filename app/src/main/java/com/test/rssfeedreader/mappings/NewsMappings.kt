package com.test.rssfeedreader.mappings

import com.test.rssfeedreader.api.model.NewsItemXmlModel
import com.test.rssfeedreader.database.model.DatabaseRssItems
import com.test.rssfeedreader.model.NewsItemModel


fun NewsItemXmlModel.mapToDatabaseRssItems(sourceTitle_: String, sourceLink_: String): DatabaseRssItems {
    return DatabaseRssItems(
        id = id,
        title = title,
        pubDate = pubDate,
        description = description,
        link = link,
        sourceTitle = sourceTitle_,
        sourceLink = sourceLink_,
        isRead = false,
        isBaned = false

    )
}


fun DatabaseRssItems.mapToNewsItemModel(): NewsItemModel {
    return NewsItemModel(
        id = id,
        title = title,
        link = link,
        pubDate = pubDate,
        sourceTitle = sourceTitle,
        sourceLink = sourceLink,
        description = description,
        isRead = isRead,
        isBaned = isBaned,
        isShowDescription = false
    )
}


fun List<DatabaseRssItems>.mapToNewsItemModelList(): List<NewsItemModel> {
    return map { it.mapToNewsItemModel() }
}


fun NewsItemModel.mapToDatabaseRssItems(): DatabaseRssItems {
    return DatabaseRssItems(
        id = id,
        title = title,
        link = link,
        pubDate = pubDate,
        sourceTitle = sourceTitle,
        sourceLink = sourceLink,
        description = description,
        isRead = isRead,
        isBaned = isBaned
    )
}


