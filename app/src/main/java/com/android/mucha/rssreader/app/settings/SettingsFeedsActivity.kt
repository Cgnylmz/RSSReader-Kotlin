package com.android.mucha.rssreader.app.settings

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.mvp.BasePresenterActivity
import kotlinx.android.synthetic.main.activity_settings_feeds.*

/**
 * Screen with Feeds.
 *
 * @author Patrik Mucha
 */
class SettingsFeedsActivity : BasePresenterActivity<SettingsFeedsView, SettingsFeedsPresenter>(), SettingsFeedsView {

    private var mAdapter: SettingsFeedsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_feeds)

        initViews()
    }

    /**
     * Initializes views for the activity.
     */
    private fun initViews() {
        mAdapter = SettingsFeedsRecyclerAdapter(this)
        mAdapter!!.setAdapterCallbacks(object : SettingsFeedsRecyclerAdapter.AdapterCallbacks {
            override fun onNewUrlAdded(url: String) {
                getPresenter()?.addNewFeed(url)

                Snackbar.make(settings_feeds_activity_container, "Add url: " + url, Snackbar.LENGTH_SHORT).show()
            }
        })
        settings_feeds_recycler.layoutManager = LinearLayoutManager(this)
        settings_feeds_recycler.setHasFixedSize(true)
        settings_feeds_recycler.adapter = mAdapter
    }

    override fun onCreatePresenter() = SettingsFeedsPresenter()

    override fun showFeedsList(data: List<SettingsFeedsItem>?) {
        mAdapter?.setData(data)
    }
}
