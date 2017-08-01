package com.android.mucha.rssreader.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Factory class for [ViewModel]s.
 *
 * @author Patrik Mucha
 */
@Singleton
class ViewModelFactory : ViewModelProvider.Factory {

    private val mViewModels: Map<Class<out ViewModel>, Provider<ViewModel>>

    @Inject
    constructor(viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) {
        mViewModels = viewModels
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // If the ViewModel is not found, we can't continue anyway.
        return mViewModels[modelClass]!!.get() as T
    }
}