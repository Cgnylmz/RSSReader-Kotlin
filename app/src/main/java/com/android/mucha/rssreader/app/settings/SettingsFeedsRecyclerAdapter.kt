package com.android.mucha.rssreader.app.settings

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mucha.rssreader.R
import com.android.mucha.rssreader.database.model.RSSFeedModel

/**
 * Holds the list of [RSSFeedModel] items.
 *
 * @author Patrik Mucha
 */
class SettingsFeedsRecyclerAdapter(context: Context) : RecyclerView.Adapter<SettingsFeedsViewHolder>() {

    /**
     * Interface definition for a callback to be invoked on adapter's actions.
     */
    interface AdapterCallbacks {
        /**
         * Called when new Feed url is about to be added.
         *
         * @param url The Feed url.
         */
        fun onNewUrlAdded(url: String)
    }

    private val mItems = mutableListOf<RSSFeedModel>()
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var mAdapterCallbacks: AdapterCallbacks? = null

    override fun getItemCount() = mItems.size + 1 // +1 because of footer view

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_TYPE_HEADER
        }
        return VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SettingsFeedsViewHolder? {
        if (viewType == VIEW_TYPE_HEADER) {
            return SettingsFeedsHeaderViewHolder(
                    mLayoutInflater.inflate(R.layout.list_item_settings_feeds_header, parent, false))
        } else {
            return SettingsFeedsItemViewHolder(
                    mLayoutInflater.inflate(R.layout.list_item_settings_feeds, parent, false))
        }
    }

    override fun onBindViewHolder(holder: SettingsFeedsViewHolder?, position: Int) {
        if (holder is SettingsFeedsItemViewHolder) {
            holder.bindView(mItems[position])
        } else if (holder is SettingsFeedsHeaderViewHolder) {
            holder.setCallback(object : SettingsFeedsHeaderViewHolder.SettingsFeedsHeaderCallbacks {
                override fun onSubmitClicked(url: String) {
                    mAdapterCallbacks?.onNewUrlAdded(url)
                }
            })
            holder.bindView()
        }
    }

    /**
     * Sets the data for the adapter.
     *
     * @param data The data to be shown. Null will clear the list.
     */
    fun setData(data: List<RSSFeedModel>?) {
        mItems.clear()
        if (data != null) {
            mItems.addAll(data)
        }
        notifyDataSetChanged()
    }

    /**
     * Sets the callbacks for this adapter.
     *
     * @param callbacks The callbacks implementation.
     */
    fun setAdapterCallbacks(callbacks: AdapterCallbacks?) {
        mAdapterCallbacks = callbacks
    }

    companion object {
        /**
         * View type for footer item.
         */
        val VIEW_TYPE_HEADER = 0
        /**
         * View type for regular item.
         */
        val VIEW_TYPE_ITEM = 1
    }

}