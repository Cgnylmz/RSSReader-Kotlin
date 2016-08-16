package com.android.mucha.rssreader.mvp

/**
 * Interface definition for creating Presenters.
 *
 * @author Patrik Mucha
 */
interface PresenterFactory<P : Presenter<out MvpView>> {

    /**
     * Creates the Presenter.
     *
     * @return The presenter.
     */
    fun create(): P
}