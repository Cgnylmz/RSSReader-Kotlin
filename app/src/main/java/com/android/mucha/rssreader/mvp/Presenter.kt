package com.android.mucha.rssreader.mvp

/**
 * Interface definition for MVP's Presenter.
 *
 * @author Patrik Mucha
 */
interface Presenter<V : MvpView> {
    /**
     * Returns the View.
     */
    fun getView(): V?

    /**
     * Binds the View.
     */
    fun bindView(view: V)

    /**
     * Unbinds the View.
     */
    fun unbindView()
}