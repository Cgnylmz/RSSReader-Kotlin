package com.android.mucha.rssreader.app.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.android.mucha.rssreader.rssloading.RSSFeed
import com.android.mucha.rssreader.rssloading.RSSFeedRepository
import com.android.mucha.rssreader.utils.EmptyLiveData
import java.util.Objects
import javax.inject.Inject

/**
 * The [ViewModel] for the [MainActivity].
 *
 * @author Patrik Mucha
 */
class MainViewModel @Inject constructor(rssFeedRepository: RSSFeedRepository) : ViewModel() {

    private val mFeedUrl = MutableLiveData<String>()
    private val mFeedData: LiveData<RSSFeed>

    init {
        mFeedData = Transformations.switchMap(mFeedUrl) { input ->
            if (input.isEmpty()) {
                EmptyLiveData.create<RSSFeed>()
            }
            rssFeedRepository.loadRSSFeed(input)
        }
    }

    /**
     * Gets the [LiveData] of [RSSFeed].
     *
     * @return The data.
     */
    fun getFeed(): LiveData<RSSFeed> {
        return mFeedData
    }

    /**
     * Initialises the load of [RSSFeed] represented by given [url].
     */
    fun setFeedUrl(url: String) {
        if (Objects.equals(mFeedUrl.value, url)) {
            return
        }
        mFeedUrl.value = url
    }
}