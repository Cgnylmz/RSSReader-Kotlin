package com.android.mucha.rssreader.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.android.mucha.rssreader.database.model.RSSFeedModel


/**
 * Dao interface for [RSSFeedModel].
 *
 * @author Patrik Mucha
 */
@Dao
interface RSSFeedModelDao {

    @Query("SELECT * FROM feeds")
    fun getAllFeeds(): LiveData<List<RSSFeedModel>>

    @Insert
    fun insertAll(vararg rssFeeds: RSSFeedModel)

    @Delete
    fun delete(rssFeed: RSSFeedModel)
}