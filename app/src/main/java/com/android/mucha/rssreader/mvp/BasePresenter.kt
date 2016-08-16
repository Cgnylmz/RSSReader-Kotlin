package com.android.mucha.rssreader.mvp

/**
 * Base Presenter class.
 *
 * @author Patrik Mucha
 */
abstract class BasePresenter<V : MvpView> : Presenter<V> {
    private var mView: V? = null

    override fun getView(): V? {
        return mView
    }

    override fun bindView(view: V) {
        mView = view
    }

    override fun unbindView() {
        mView = null
    }
}