package com.android.mucha.rssreader.app.settings

import com.android.mucha.rssreader.mvp.BasePresenter

/**
 * Presenter for [SettingsFeedsActivity].
 *
 * @author Patrik Mucha
 */
class SettingsFeedsPresenter : BasePresenter<SettingsFeedsView>() {

    /**
     * Cache for items.
     */
    private var mItems: List<SettingsFeedsItem>? = null

    override fun bindView(view: SettingsFeedsView) {
        super.bindView(view)

        // Show cached Feed items if they are available - e.g. after rotation
        if (mItems != null) {
            view.showFeedsList(mItems)
        }
    }

    fun addNewFeed(url: String) {
        // TODO: check if the Feed url is valid, save and load Feed list
    }

    fun loadFeedList() {
        // TODO: load list from DB
    }
}