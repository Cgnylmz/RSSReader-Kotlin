package com.android.mucha.rssreader.app.settings

import android.view.View
import com.android.mucha.rssreader.database.model.RSSFeedModel
import kotlinx.android.synthetic.main.list_item_settings_feeds.view.*

/**
 * Holds all the views for the [SettingsFeedsRecyclerAdapter] item.
 *
 * @author Patrik Mucha
 */
class SettingsFeedsItemViewHolder(private val mItemView: View) : SettingsFeedsViewHolder(mItemView) {

    /**
     * Reads the content of given [RSSFeedModel] item and sets up all the views.
     *
     * @param item The item holding the data.
     */
    fun bindView(item: RSSFeedModel) {
        mItemView.item_settings_feeds_title.text = item.mName
        mItemView.item_settings_feeds_subtitle.text = item.mUri
    }
}