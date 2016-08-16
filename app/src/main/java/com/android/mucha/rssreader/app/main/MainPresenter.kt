package com.android.mucha.rssreader.app.main

import com.android.mucha.rssreader.mvp.BasePresenter
import com.android.mucha.rssreader.rssloading.RSSFeed
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
     * Loads an RSS Feed from the given [url].
     *
     * @param url The string representation of url.
     */
    fun loadRSSFeed(url: String) {
        getView()?.showLoadingProgress()

        val loadRSSObservable = Single.fromCallable {
            RSSLoader().loadRSS(url)
        }

        loadRSSObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleSubscriber<RSSFeed>() {
                    override fun onSuccess(value: RSSFeed) {
                        getView()?.showRSSFeed(value.mFeedItems.toList())
                    }

                    override fun onError(error: Throwable) {
                        getView()?.showError()
                    }
                })
    }
}