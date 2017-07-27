package com.android.mucha.rssreader.app.settings

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.RSSReaderApplication
import com.android.mucha.rssreader.database.dao.RSSFeedModelDao
import kotlinx.android.synthetic.main.activity_settings_feeds.*
import javax.inject.Inject


/**
 * Screen with Feeds.
 *
 * @author Patrik Mucha
 */
class SettingsFeedsActivity : LifecycleActivity() {

    @Inject
    lateinit var mRssFeedModelDao: RSSFeedModelDao

    private var mAdapter: SettingsFeedsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        RSSReaderApplication[this].mApplicationComponent.injectSettingsFeedActivity(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_feeds)

        mAdapter = SettingsFeedsRecyclerAdapter(this)
        mAdapter!!.setAdapterCallbacks(object : SettingsFeedsRecyclerAdapter.AdapterCallbacks {
            override fun onNewUrlAdded(url: String) {
                // TODO: check the url and insert valid url into database
                Snackbar.make(settings_feeds_activity_container, "Add url: " + url, Snackbar.LENGTH_SHORT).show()
            }
        })

        initViews()
        loadFeeds()
    }

    /**
     * Initializes views for the activity.
     */
    private fun initViews() {
        settings_feeds_recycler.layoutManager = LinearLayoutManager(this)
        settings_feeds_recycler.setHasFixedSize(true)
        settings_feeds_recycler.adapter = mAdapter
    }

    /**
     * Loads the list of Feeds.
     */
    private fun loadFeeds() {
        val viewModel = ViewModelProviders.of(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
                // TODO: Create some factory class. This code will be mostly the same.
                @Suppress("UNCHECKED_CAST")
                return SettingsFeedsViewModel(mRssFeedModelDao) as T
            }

        })[SettingsFeedsViewModel::class.java]
        viewModel.getFeeds().observe(this, Observer {
            mAdapter?.setData(it)
        })
    }
}
