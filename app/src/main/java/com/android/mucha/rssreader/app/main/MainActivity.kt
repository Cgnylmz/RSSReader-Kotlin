package com.android.mucha.rssreader.app.main

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.RSSReaderApplication
import com.android.mucha.rssreader.app.settings.SettingsActivity
import com.android.mucha.rssreader.rssloading.RSSFeedRepository
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Main activity class.
 *
 * @author Patrik Mucha
 */
class MainActivity : LifecycleActivity() {

    @Inject
    lateinit var mRssFeedRepository: RSSFeedRepository

    private var mAdapter: RSSFeedRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        RSSReaderApplication[this].mApplicationComponent.injectMainActivity(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    /**
     * Initializes views for the activity.
     */
    private fun initViews() {
        mAdapter = RSSFeedRecyclerAdapter(this)
        main_rss_feed.layoutManager = LinearLayoutManager(this)
        main_rss_feed.setHasFixedSize(true)
        main_rss_feed.adapter = mAdapter

        val viewModel = ViewModelProviders.of(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
                // TODO: Create some factory class. This code will be mostly the same.
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(mRssFeedRepository) as T
            }

        })[MainViewModel::class.java]
        viewModel.getFeed().observe(this, Observer {
            main_rss_url.isEnabled = true
            main_rss_url_load.isEnabled = true

            mAdapter?.setData(it?.mFeedItems)
        })

        main_rss_url_load.setOnClickListener { view ->
            main_rss_url.isEnabled = false
            main_rss_url_load.isEnabled = false

            viewModel.setFeedUrl(main_rss_url.text.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
