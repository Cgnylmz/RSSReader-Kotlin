package com.android.mucha.rssreader

import android.content.Context
import com.android.mucha.rssreader.dagger.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [Context] or [android.app.Application] to create.
 */
@Module
class AppModule(private val mApplication: RSSReaderApplication) {
    /**
     * Allow the application context to be injected but require that it be annotated with
     * [@Annotation][ForApplication] to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context {
        return mApplication
    }
}