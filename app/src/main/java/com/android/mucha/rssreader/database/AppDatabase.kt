package com.android.mucha.rssreader.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.mucha.rssreader.database.dao.RSSFeedModelDao
import com.android.mucha.rssreader.database.model.RSSFeedModel

/**
 * Base database class.
 *
 * @author Patrik Mucha
 */
@Database(entities = arrayOf(RSSFeedModel::class), version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract val rssFeedModelDao: RSSFeedModelDao

    companion object {
        const val DATABASE_NAME = "rss-reader.db"
        const val DATABASE_VERSION = 1
    }
}