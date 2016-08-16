package com.android.mucha.rssreader.mvp

import android.content.Context
import android.support.v4.content.Loader

/**
 * Loads and holds the Presenter for View (Activity or Fragment).
 *
 * @author Patrik Mucha
 */
class PresenterLoader<P : Presenter<out MvpView>>(context: Context, val mPresenterFactory: PresenterFactory<P>) :
        Loader<P>(context) {

    private var mPresenter: P? = null

    override fun onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter)
        } else {
            forceLoad()
        }
    }

    override fun onForceLoad() {
        mPresenter = mPresenterFactory.create()
        deliverResult(mPresenter)
    }

    override fun onReset() {
        mPresenter = null
    }
}