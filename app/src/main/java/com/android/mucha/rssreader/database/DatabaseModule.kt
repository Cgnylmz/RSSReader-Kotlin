package com.android.mucha.rssreader.database

import com.android.mucha.rssreader.database.dao.RSSFeedModelDao
import com.android.mucha.rssreader.database.model.RSSFeedModel
import dagger.Module
import dagger.Provides
import java.sql.SQLException
import javax.inject.Singleton

/**
 * Base database module.
 *
 * @author Patrik Mucha
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRSSFeedModelDao(databaseHelper: DatabaseHelper): RSSFeedModelDao {
        try {
            return databaseHelper.getDao(RSSFeedModel::class.java)
        } catch(e: SQLException) {
            throw RuntimeException("Can't create RSSFeedModelDao.", e)
        }
    }
}