package com.android.mucha.rssreader.app.main

import com.android.mucha.rssreader.mvp.MvpView
import com.android.mucha.rssreader.rssloading.RSSFeedItem

/**
 * Interface definition for main view.
 *
 * @author Patrik Mucha
 */
interface MainView : MvpView {

    /**
     * Shows the loading progress while loading RSS Feed.
     */
    fun showLoadingProgress()

    /**
     * Shows the RSS Feed once it's loaded.
     *
     * @param data The loaded data.
     */
    fun showRSSFeed(data: List<RSSFeedItem>)

    /**
     * Shows the error on RSS loading failed.
     */
    fun showError()
}