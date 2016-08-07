package com.android.mucha.rssreader.app.main

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.android.mucha.rssreader.rssloading.RSSFeedItem
import kotlinx.android.synthetic.main.list_item_rss_feed.view.*
import java.text.DateFormat

/**
 * Holds all the views for the [RSSFeedRecyclerAdapter] item.
 *
 * @author Patrik Mucha
 */
class RSSFeedViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    /**
     * Reads the content of given [RSSFeedItem] and sets up all the views.
     */
    fun setRSSFeedItem(item: RSSFeedItem) {
        mItemView.feed_item_title.text = item.mTitle
        mItemView.feed_item_description.text = item.mDescription
        mItemView.feed_item_pubdate.text = DateFormat.getInstance().format(item.mPubDate)
        mItemView.setOnClickListener { view ->
            if (item.mLink != null) {
                val intent = Intent(Intent.ACTION_VIEW, item.mLink)
                mItemView.context.startActivity(intent)
            }
        }
    }
}