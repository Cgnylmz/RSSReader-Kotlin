package com.android.mucha.rssreader

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Main Application class.
 *
 * @author Patrik Mucha
 */
class RSSReaderApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var mDispatchingActivityInjector: DispatchingAndroidInjector<Activity>

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
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mDispatchingActivityInjector
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