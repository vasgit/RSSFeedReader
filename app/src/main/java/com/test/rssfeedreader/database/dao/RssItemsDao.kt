package com.test.rssfeedreader.database.dao

import androidx.room.*
import com.test.rssfeedreader.database.model.DatabaseRssItems

@Dao
interface RssItemsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(profileInfo: DatabaseRssItems)


    @Update
    fun update(profileInfo: DatabaseRssItems)


    @Query("DELETE FROM ${DatabaseRssItems.TABLE_NAME}")
    fun deleteAll()


    @Query(
        """
        SELECT * FROM ${DatabaseRssItems.TABLE_NAME} 
        WHERE ${DatabaseRssItems.SOURCE_LINK} = :link AND
        ${DatabaseRssItems.IS_BANED} = 0
        """
    )
    fun getByLink(link: String): List<DatabaseRssItems>


    @Query(
        """
        SELECT * FROM ${DatabaseRssItems.TABLE_NAME} 
        WHERE ${DatabaseRssItems.SOURCE_LINK} = :link
        """
    )
    fun getByLinkAllData(link: String): List<DatabaseRssItems>


}