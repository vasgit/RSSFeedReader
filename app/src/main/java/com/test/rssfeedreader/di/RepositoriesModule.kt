package com.test.rssfeedreader.di

import com.test.rssfeedreader.data.repositories.NewsRepository
import com.test.rssfeedreader.data.repositories.NewsRepositoryImpl
import com.test.rssfeedreader.di.utils.get
import org.koin.dsl.module

@Suppress("USELESS_CAST")
val repositoriesModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl(
            serverDataStore = get(NEWS_SERVER_DATA_STORE),
            databaseDataStore = get(NEWS_DATABASE_DATA_STORE),
            connectionProvider = get()

        )
    }

}


