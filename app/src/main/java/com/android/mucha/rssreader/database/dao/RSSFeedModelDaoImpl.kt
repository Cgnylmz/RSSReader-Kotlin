package com.android.mucha.rssreader.database.dao

import com.android.mucha.rssreader.database.model.RSSFeedModel
import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource

/**
 * Base [RSSFeedModelDao] implementation.
 *
 * @author Patrik Mucha
 */
class RSSFeedModelDaoImpl(connectionSource: ConnectionSource) : BaseDaoImpl<RSSFeedModel, Int>(connectionSource,
        RSSFeedModel::class.java), RSSFeedModelDao {
}