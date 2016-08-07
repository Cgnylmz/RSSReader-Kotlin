package com.android.mucha.rssreader.app.main

import com.android.mucha.rssreader.rssloading.RSSFeed
import com.android.mucha.rssreader.rssloading.RSSLoader
import rx.Single
import rx.SingleSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Implementation of [MainPresenter].
 *
 * @author Patrik Mucha
 */
class MainPresenterImpl(val mMainView: MainView) : MainPresenter {

    override fun loadRSSFeed(url: String) {
        mMainView.showLoadingProgress()

        val loadRSSObservable = Single.fromCallable {
            RSSLoader().loadRSS(url)
        }

        loadRSSObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleSubscriber<RSSFeed>() {
                    override fun onSuccess(value: RSSFeed) {
                        mMainView.showRSSFeed(value.mFeedItems.toList())
                    }

                    override fun onError(error: Throwable) {
                        mMainView.showError()
                    }
                })
    }
}