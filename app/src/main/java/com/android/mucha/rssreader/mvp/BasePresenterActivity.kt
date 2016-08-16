package com.android.mucha.rssreader.mvp

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity

/**
 * Base activity class.
 *
 * @author Patrik Mucha
 */
abstract class BasePresenterActivity<V : MvpView, P : Presenter<V>> : AppCompatActivity(),
        LoaderManager.LoaderCallbacks<P> {

    private var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportLoaderManager.initLoader(PRESENTER_LOADER_ID, Bundle(), this)
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.bindView(this as V)
    }

    override fun onStop() {
        super.onStop()
        mPresenter?.unbindView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter = null
    }

    fun getPresenter(): P? = mPresenter

    override fun onLoaderReset(loader: Loader<P>?) {
        mPresenter = null
    }

    override fun onLoadFinished(loader: Loader<P>?, presenter: P) {
        mPresenter = presenter
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<P>? {
        return PresenterLoader(this, object : PresenterFactory<P> {
            override fun create(): P = onCreatePresenter()
        })
    }

    /**
     * Called when creating a Presenter.
     */
    abstract fun onCreatePresenter(): P

    companion object {
        /**
         * Identifier for Presenter's loader.
         */
        private val PRESENTER_LOADER_ID = 1;
    }
}