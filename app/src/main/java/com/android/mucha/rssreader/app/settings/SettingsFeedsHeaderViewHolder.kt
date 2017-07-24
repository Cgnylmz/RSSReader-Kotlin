package com.android.mucha.rssreader.app.settings

import android.view.View
import kotlinx.android.synthetic.main.list_item_settings_feeds_header.view.*

/**
 * Holds the header view for the [SettingsFeedsRecyclerAdapter].
 *
 * @author Patrik Mucha
 */
class SettingsFeedsHeaderViewHolder(private val mItemView: View) : SettingsFeedsViewHolder(mItemView) {

    /**
     * Interface definition for a callback to be invoked on view's actions.
     */
    interface SettingsFeedsHeaderCallbacks {
        /**
         * Called on Submit button clicks.
         *
         * @param url The entered url.
         */
        fun onSubmitClicked(url: String)
    }

    private var mCallbacks: SettingsFeedsHeaderCallbacks? = null

    /**
     * Binds the view.
     */
    fun bindView() {
        mItemView.item_settings_feeds_header_submit.setOnClickListener { view ->
            mCallbacks?.onSubmitClicked(mItemView.item_settings_feeds_header_uri.text.toString())
        }
    }

    /**
     * Set the callbacks for the view.
     *
     * @param callbacks The calbacks implementation.
     */
    fun setCallback(callbacks: SettingsFeedsHeaderCallbacks?) {
        mCallbacks = callbacks
    }
}