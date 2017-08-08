package com.android.mucha.rssreader.database

import android.app.Application
import android.arch.persistence.room.Room
import com.android.mucha.rssreader.database.dao.RSSFeedModelDao
import dagger.Module
import dagger.Provides
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
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build();
    }

    @Provides
    fun provideRSSFeedModelDao(database: AppDatabase): RSSFeedModelDao {
        return database.rssFeedModelDao
    }
}