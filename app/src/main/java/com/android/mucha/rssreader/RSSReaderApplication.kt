package com.android.mucha.rssreader

import android.app.Application
import android.content.Context

/**
 * Main Application class.
 *
 * @author Patrik Mucha
 */
class RSSReaderApplication : Application() {

    var mApplicationComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        initLibraries()
    }

    /**
     * Initializes all the libraries.
     */
    private fun initLibraries() {
        initDagger()
    }

    /**
     * Initializes Dagger's application component.
     */
    private fun initDagger() {
        mApplicationComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
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