package com.android.mucha.rssreader.app.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.rssloading.RSSFeedItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity class.
 *
 * @author Patrik Mucha
 */
class MainActivity : AppCompatActivity(), MainView {

    private var presenter: MainPresenter? = null
    private var rssFeedAdapter: RSSFeedRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenterImpl(this)

        initViews()
    }

    /**
     * Initializes views for the activity.
     */
    private fun initViews() {
        main_rss_url_load.setOnClickListener { view ->
            presenter?.loadRSSFeed(main_rss_url.text.toString())
        }

        rssFeedAdapter = RSSFeedRecyclerAdapter(this)
        main_rss_feed.layoutManager = LinearLayoutManager(this)
        main_rss_feed.setHasFixedSize(true)
        main_rss_feed.adapter = rssFeedAdapter
    }

    /* Overriden methods. */

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
}
