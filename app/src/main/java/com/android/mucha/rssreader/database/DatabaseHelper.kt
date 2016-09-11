package com.android.mucha.rssreader.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.android.mucha.rssreader.dagger.ForApplication
import com.android.mucha.rssreader.database.model.RSSFeedModel
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Base database helper implementation.
 *
 * @author Patrik Mucha
 */
@Singleton
class DatabaseHelper : OrmLiteSqliteOpenHelper {

    @Inject
    constructor(@ForApplication context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            Log.d(TAG, "Creating database '$DATABASE_NAME' with version $DATABASE_VERSION")
            TableUtils.createTable(connectionSource, RSSFeedModel::class.java)
        } catch (e: SQLException) {
            Log.e(TAG, "Unable to create database '$DATABASE_NAME'", e);
            throw RuntimeException(e);
        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int,
                           newVersion: Int) {
    }

    companion object {
        /**
         * Tag for logging.
         */
        private val TAG = DatabaseHelper::class.java.name
        /**
         * The name of the database.
         */
        private val DATABASE_NAME = "rss-reader.db"

        /**
         * The version of the database.
         */
        private val DATABASE_VERSION = 1
    }
}