package com.android.mucha.rssreader.app.main

/**
 * Interface definition for a presenter for main view.
 *
 * @author Patrik Mucha
 */
interface MainPresenter {

    /**
     * Loads an RSS Feed from the given [url].
     *
     * @param url The string representation of url.
     */
    fun loadRSSFeed(url: String)
}