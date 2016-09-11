package com.android.mucha.rssreader.database.model

import android.provider.BaseColumns
import com.android.mucha.rssreader.database.dao.RSSFeedModelDaoImpl
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Model to store RSS Feed info.
 *
 * @author Patrik Mucha
 */
@DatabaseTable(tableName = RSSFeedModel.TABLE_NAME, daoClass = RSSFeedModelDaoImpl::class)
class RSSFeedModel {

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    var mId: Int? = null
        private set

    @DatabaseField(columnName = COLUMN_FEED_URI)
    val mUri: String

    @DatabaseField(columnName = COLUMN_FEED_NAME)
    val mName: String

    constructor(uri: String, name: String) {
        mUri = uri
        mName = name
    }

    companion object {
        /**
         * Table name.
         */
        const val TABLE_NAME = "feeds"

        /**
         * "id" column name.
         */
        const val COLUMN_ID = BaseColumns._ID
        /**
         * Column for Feed's uri.
         */
        const val COLUMN_FEED_URI = "feed_uri"
        /**
         * Column for Feed's name.
         */
        const val COLUMN_FEED_NAME = "feed_name"
    }
}