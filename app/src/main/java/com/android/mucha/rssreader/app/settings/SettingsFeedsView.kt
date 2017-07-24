package com.android.mucha.rssreader.app.settings

import com.android.mucha.rssreader.mvp.MvpView

/**
 * View for [SettingsFeedsActivity].
 *
 * @author Patrik Mucha
 */
interface SettingsFeedsView : MvpView {

    /**
     * Shows the List with Feeds once it's loaded.
     *
     * @param data The loaded data.
     */
    fun showFeedsList(data: List<SettingsFeedsItem>?)
}