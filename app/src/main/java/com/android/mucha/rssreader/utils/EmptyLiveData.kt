package com.android.mucha.rssreader.utils

import android.arch.lifecycle.LiveData

/**
 * Empty [LiveData] extension.
 *
 * @author Patrik Mucha
 */
class EmptyLiveData<T> private constructor() : LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return EmptyLiveData()
        }
    }
}