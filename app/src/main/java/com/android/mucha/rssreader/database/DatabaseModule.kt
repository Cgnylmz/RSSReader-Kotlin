package com.android.mucha.rssreader.database

import android.arch.persistence.room.Room
import android.content.Context
import com.android.mucha.rssreader.dagger.ForApplication
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
    fun provideDatabase(@ForApplication context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build();
    }

    @Provides
    fun provideRSSFeedModelDao(database: AppDatabase): RSSFeedModelDao {
        return database.rssFeedModelDao
    }
}