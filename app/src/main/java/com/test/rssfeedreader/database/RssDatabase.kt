package com.test.rssfeedreader.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.rssfeedreader.database.RssDatabase.Companion.VERSION
import com.test.rssfeedreader.database.dao.RssItemsDao
import com.test.rssfeedreader.database.model.DatabaseRssItems

@Database(entities = [
    DatabaseRssItems::class
], version = VERSION)
abstract class RssDatabase : RoomDatabase() {

    companion object {

        const val NAME = "rss_database.db"
        const val VERSION = 1

    }

    abstract val rssItemsDao: RssItemsDao

}