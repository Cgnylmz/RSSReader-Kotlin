package com.android.mucha.rssreader.app.main

import com.android.mucha.rssreader.mvp.BasePresenter
import com.android.mucha.rssreader.rssloading.RSSFeed
import com.android.mucha.rssreader.rssloading.RSSFeedItem
import com.android.mucha.rssreader.rssloading.RSSLoader
import rx.Single
import rx.SingleSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Presenter for main view.
 *
 * @author Patrik Mucha
 */
class MainPresenter : BasePresenter<MainView>() {

    /**
     * Cache for Feed items.
     */
    private var mFeedItems: List<RSSFeedItem>? = null

    override fun bindView(view: MainView) {
        super.bindView(view)

        // Show cached Feed items if they are available - e.g. after rotation
        if (mFeedItems != null) {
            view.showRSSFeed(mFeedItems!!)
        }
    }

    /**
     * Loads an RSS Feed from the given [url].
     *
     * @param url The string representation of url.
     */
    fun loadRSSFeed(url: String) {
        // Reset cached Feed items on start loading.
        mFeedItems = null
        getView()?.showLoadingProgress()

        val loadRSSObservable = provideLoadRSSRxSingle(url)

        loadRSSObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleSubscriber<RSSFeed>() {
                    override fun onSuccess(value: RSSFeed) {
                        mFeedItems = value.mFeedItems
                        getView()?.showRSSFeed(mFeedItems!!)
                    }

                    override fun onError(error: Throwable) {
                        getView()?.showError()
                    }
                })
    }

    private fun provideLoadRSSRxSingle(url: String): Single<RSSFeed> {
        return Single.fromCallable {
            RSSLoader().loadRSS(url)
        }
    }
}