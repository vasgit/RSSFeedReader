package com.test.rssfeedreader.data.repositories

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.utils.model.RepositoryResult
import com.test.rssfeedreader.data.base.NewsData
import com.test.rssfeedreader.model.NewsItemModel

interface NewsRepository : NewsData<
    RepositoryResult<List<NewsItemModel>>,
    RepositoryResult<RssXmlModel>,
    RepositoryResult<NewsItemModel>
>