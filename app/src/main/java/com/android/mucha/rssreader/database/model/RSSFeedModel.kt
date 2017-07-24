package com.android.mucha.rssreader.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.provider.BaseColumns

/**
 * Model to store RSS Feed info.
 *
 * @author Patrik Mucha
 */
@Entity(tableName = RSSFeedModel.TABLE_NAME)
class RSSFeedModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var mId: Int = 0
        get set

    @ColumnInfo(name = COLUMN_FEED_URI)
    var mUri: String? = null
        get set

    @ColumnInfo(name = COLUMN_FEED_NAME)
    var mName: String? = null
        get set

    constructor()

    constructor(id: Int, uri: String, name: String) {
        mId = id;
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