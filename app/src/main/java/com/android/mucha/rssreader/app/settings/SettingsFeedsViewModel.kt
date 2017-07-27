package com.android.mucha.rssreader.app.settings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.mucha.rssreader.database.dao.RSSFeedModelDao
import com.android.mucha.rssreader.database.model.RSSFeedModel
import javax.inject.Inject

/**
 * The [ViewModel] for the [SettingsFeedsActivity].
 *
 * @author Patrik Mucha
 */
class SettingsFeedsViewModel @Inject constructor(rssFeedModelDao: RSSFeedModelDao) : ViewModel() {

    private val mFeeds: LiveData<List<RSSFeedModel>> = rssFeedModelDao.getAllFeeds()

    /**
     * Returns the [LiveData] of [RSSFeedModel] list.
     *
     * @return The data.
     */
    fun getFeeds(): LiveData<List<RSSFeedModel>> {
        return mFeeds
    }
}