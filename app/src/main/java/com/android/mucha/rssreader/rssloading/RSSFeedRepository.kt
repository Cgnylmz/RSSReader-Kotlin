package com.android.mucha.rssreader.rssloading

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import rx.Single
import rx.SingleSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Repository/ Provider of [RSSFeed] data to easily replace the logic of loading RSS Feeds.
 *
 * @author Patrik Mucha
 */
class RSSFeedRepository @Inject constructor() {

    /**
     * Loads the Feed represented by given [url].
     *
     * @return The [LiveData] of [RSSFeed].
     */
    fun loadRSSFeed(url: String): LiveData<RSSFeed> {
        val data = MutableLiveData<RSSFeed>()
        val loadRSSObservable = Single.fromCallable {
            RSSLoader().loadRSS(url)
        }

        loadRSSObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleSubscriber<RSSFeed>() {
                    override fun onSuccess(value: RSSFeed) {
                        data.value = value
                    }

                    override fun onError(error: Throwable) {
                        Log.d(TAG, "Error while loading RSS Feed.", error)
                        data.value = RSSFeed()
                    }
                })

        return data
    }

    companion object {
        const val TAG = "RSSFeedRepository"
    }
}