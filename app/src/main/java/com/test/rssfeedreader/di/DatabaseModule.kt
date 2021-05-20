package com.test.rssfeedreader.di

import androidx.room.Room
import com.test.rssfeedreader.database.RssDatabase
import com.test.rssfeedreader.database.migrations.MIGRATIONS
import com.test.rssfeedreader.database.tables.NewsItemsTable
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            RssDatabase::class.java,
            RssDatabase.NAME
        ).addMigrations(*MIGRATIONS)
            .allowMainThreadQueries().build()
    }

    single { get<RssDatabase>().rssItemsDao }

    single { NewsItemsTable(get()) }

}




