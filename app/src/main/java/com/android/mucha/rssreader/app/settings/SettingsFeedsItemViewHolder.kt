package com.android.mucha.rssreader.app.settings

import android.view.View
import kotlinx.android.synthetic.main.list_item_settings_feeds.view.*

/**
 * Holds all the views for the [SettingsFeedsRecyclerAdapter] item.
 *
 * @author Patrik Mucha
 */
class SettingsFeedsItemViewHolder(private val mItemView: View) : SettingsFeedsViewHolder(mItemView) {

    /**
     * Reads the content of given [SettingsFeedsItem] and sets up all the views.
     *
     * @param item The item holding the data.
     */
    fun bindView(item: SettingsFeedsItem) {
        mItemView.item_settings_feeds_title.text = item.mName
        mItemView.item_settings_feeds_subtitle.text = item.mUri
    }
}