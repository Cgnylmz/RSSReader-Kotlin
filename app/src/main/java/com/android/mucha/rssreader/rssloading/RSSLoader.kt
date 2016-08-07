package com.android.mucha.rssreader.rssloading

import android.support.annotation.WorkerThread
import com.android.mucha.rssreader.rssloading.exceptions.RSSLoaderException
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * Provides method to load [RSSFeed] from specified url.
 *
 * @author Patrik Mucha
 */
class RSSLoader {

    /**
     * Loads the RSS from the given url.
     *
     * The method call is blocking until the result is returned. Call this method on a worker thread.
     *
     * @return Parsed RSS Feed. Never null.
     * @throws RSSLoaderException if the execution fails (wrong input or I/O related errors).
     */
    @WorkerThread
    @Throws(RSSLoaderException::class)
    fun loadRSS(url: String): RSSFeed {
        // Prevent IllegalArgumentException and skip next execution - but anyway inform UI about error
        val httpUrl = HttpUrl.parse(url) ?: throw RSSLoaderException("Given url has invalid format.", null)
        try {
            val okHttpClient = OkHttpClient()
            val request = Request.Builder().url(httpUrl).build();
            // IllegalStateException shouldn't happen here, since we are executing new request
            val response = okHttpClient.newCall(request).execute()

            if (response.isSuccessful) {
                val body = response.body()
                val result = RSSParser().parseRSS(body.byteStream())
                body.close()
                return result
            }
        } catch (e: IOException) {
            throw RSSLoaderException("IOException during executing request.", e)
        }
        return RSSFeed()
    }
}