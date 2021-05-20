package com.test.rssfeedreader.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.rssfeedreader.database.model.DatabaseRssItems.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DatabaseRssItems(
    @PrimaryKey @ColumnInfo(name = ID) var id: String,
    @ColumnInfo(name = TITLE) var title: String,
    @ColumnInfo(name = LINK) var link: String,
    @ColumnInfo(name = PUB_DATE) var pubDate: String,
    @ColumnInfo(name = SOURCE_TITLE) var sourceTitle: String,
    @ColumnInfo(name = SOURCE_LINK) var sourceLink: String,
    @ColumnInfo(name = DESCRIPTION) var description: String,
    @ColumnInfo(name = IS_READ) var isRead: Boolean,
    @ColumnInfo(name = IS_BANED) var isBaned: Boolean,
) {


    companion object {

        const val TABLE_NAME = "rss_items"
        const val ID = "id"
        const val TITLE = "title"
        const val LINK = "link"
        const val PUB_DATE = "pub_data"
        const val SOURCE_TITLE = "source_title"
        const val SOURCE_LINK = "source_link"
        const val DESCRIPTION = "description"
        const val IS_READ = "is_read"
        const val IS_BANED = "is_baned"

    }


}