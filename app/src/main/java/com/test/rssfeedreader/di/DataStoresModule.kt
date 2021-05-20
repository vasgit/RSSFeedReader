package com.test.rssfeedreader.di

import com.test.rssfeedreader.data.stores.NewsDataStore
import com.test.rssfeedreader.data.stores.NewsDatabaseDataStore
import com.test.rssfeedreader.data.stores.NewsServerDataStore
import com.test.rssfeedreader.di.utils.single
import org.koin.dsl.module

const val NEWS_SERVER_DATA_STORE = "news_server_data_store"
const val NEWS_DATABASE_DATA_STORE = "news_database_data_store"

@Suppress("USELESS_CAST")
val dataStoresModule = module {

    single<NewsDataStore>(NEWS_SERVER_DATA_STORE) { NewsServerDataStore(get()) }
    single<NewsDataStore>(NEWS_DATABASE_DATA_STORE) { NewsDatabaseDataStore(get()) }

}
