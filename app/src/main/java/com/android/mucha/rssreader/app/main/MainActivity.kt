package com.android.mucha.rssreader.app.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.app.settings.SettingsActivity
import com.android.mucha.rssreader.mvp.BasePresenterActivity
import com.android.mucha.rssreader.rssloading.RSSFeedItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity class.
 *
 * @author Patrik Mucha
 */
class MainActivity : BasePresenterActivity<MainView, MainPresenter>(), MainView {
    private var rssFeedAdapter: RSSFeedRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    /**
     * Initializes views for the activity.
     */
    private fun initViews() {
        main_rss_url_load.setOnClickListener { view ->
            getPresenter()?.loadRSSFeed(main_rss_url.text.toString())
        }

        rssFeedAdapter = RSSFeedRecyclerAdapter(this)
        main_rss_feed.layoutManager = LinearLayoutManager(this)
        main_rss_feed.setHasFixedSize(true)
        main_rss_feed.adapter = rssFeedAdapter
    }

    /* Overriden methods. */
    override fun onCreatePresenter() = MainPresenter()

    override fun showRSSFeed(data: List<RSSFeedItem>) {
        main_rss_url.isEnabled = true
        main_rss_url_load.isEnabled = true
        rssFeedAdapter?.setData(data)
    }

    override fun showLoadingProgress() {
        main_rss_url.isEnabled = false
        main_rss_url_load.isEnabled = false
        rssFeedAdapter?.setData(emptyList())
    }

    override fun showError() {
        main_rss_url.isEnabled = true
        main_rss_url_load.isEnabled = true
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
