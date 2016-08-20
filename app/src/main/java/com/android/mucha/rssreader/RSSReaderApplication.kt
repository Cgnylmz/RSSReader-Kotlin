package com.android.mucha.rssreader

import android.app.Application
import android.content.Context

/**
 * Main Application class.
 *
 * @author Patrik Mucha
 */
class RSSReaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initLibraries()
    }

    /**
     * Initializes all the libraries.
     */
    private fun initLibraries() {
    }

    companion object {

        /**
         * Gets the application instance.
         *
         * @param context The context.
         * @return The application instance.
         */
        operator fun get(context: Context): RSSReaderApplication {
            return context.applicationContext as RSSReaderApplication
        }
    }
}