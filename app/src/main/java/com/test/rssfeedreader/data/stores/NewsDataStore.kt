package com.test.rssfeedreader.data.stores

import com.test.rssfeedreader.api.model.RssXmlModel
import com.test.rssfeedreader.data.base.NewsData
import com.test.rssfeedreader.utils.model.Result
import com.test.rssfeedreader.model.NewsItemModel

interface NewsDataStore : NewsData<
    Result<List<NewsItemModel>>,
    Result<RssXmlModel>,
    Result<NewsItemModel>,
>
