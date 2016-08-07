package com.android.mucha.rssreader.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Provides utility methods related to date.
 *
 * @author Patrik Mucha
 */
class DateUtils {

    /**
     * We don't want to create instances.
     */
    private constructor()

    companion object {

        /**
         * Format for date in RFC 822.
         */
        private val RFC822_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z"

        /**
         * Parses and returns given date in milliseconds.
         *
         * @param dateString The string representation of date. Must be in [RFC822]() format.
         * @return The date in millis, or -1 if the date format is invalid.
         */
        fun parseDateFromRFC822(dateString: String?): Long {
            try {
                return SimpleDateFormat(RFC822_DATE_FORMAT, Locale.ENGLISH).parse(dateString).time
            } catch (e: ParseException) {
                return -1;
            }
        }
    }
}