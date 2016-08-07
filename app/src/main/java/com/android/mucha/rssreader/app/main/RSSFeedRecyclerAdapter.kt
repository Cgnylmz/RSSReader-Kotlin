package com.android.mucha.rssreader.app.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.rssloading.RSSFeedItem

/**
 * Holds the list of [RSSFeedItem]s.
 *
 * @author Patrik Mucha
 */
class RSSFeedRecyclerAdapter : RecyclerView.Adapter<RSSFeedViewHolder> {

    private val mItems = mutableListOf<RSSFeedItem>()
    private val mLayoutInflater: LayoutInflater

    constructor(context: Context) {
        mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getItemCount() = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RSSFeedViewHolder? {
        return RSSFeedViewHolder(mLayoutInflater.inflate(R.layout.list_item_rss_feed, parent, false))
    }

    override fun onBindViewHolder(holder: RSSFeedViewHolder?, position: Int) {
        holder?.setRSSFeedItem(mItems[position])
    }

    /**
     * Sets the data for the adapter.
     *
     * @param data The data to be shown. Null will clear the list.
     */
    fun setData(data: List<RSSFeedItem>?) {
        mItems.clear()
        if (data != null) {
            mItems.addAll(data)
        }
        notifyDataSetChanged()
    }
}