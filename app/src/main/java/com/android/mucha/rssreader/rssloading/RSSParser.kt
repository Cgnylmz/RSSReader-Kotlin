package com.android.mucha.rssreader.rssloading

import android.support.annotation.WorkerThread
import org.xml.sax.InputSource
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory

/**
 * Provides method for parsing RSS Feed's xml.
 *
 * @author Patrik Mucha
 */
class RSSParser {

    /**
     * Parses the given input stream.
     *
     * The method call is blocking until the parsing is finished.
     */
    @WorkerThread
    fun parseRSS(inputStream: InputStream): RSSFeed {
        // TODO: catch the exceptions - throw our own RSSParserException
        val factory = SAXParserFactory.newInstance()
        val parser = factory.newSAXParser()
        val handler = RSSXmlHandler();
        val xmlReader = parser.xmlReader

        val source = InputSource(inputStream)
        xmlReader.contentHandler = handler;
        xmlReader.parse(source);

        return handler.rssFeed
    }
}