package com.android.mucha.rssreader.rssloading

import android.support.annotation.WorkerThread
import com.android.mucha.rssreader.rssloading.exceptions.RSSParserException
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.ParserConfigurationException
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
     *
     * @return The [RSSFeed] instance. Never null.
     * @throws RSSParserException if the parsing fails.
     */
    @WorkerThread
    @Throws(RSSParserException::class)
    fun parseRSS(inputStream: InputStream): RSSFeed {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = RSSXmlHandler();
            val xmlReader = parser.xmlReader

            val source = InputSource(inputStream)
            xmlReader.contentHandler = handler;
            xmlReader.parse(source);

            return handler.rssFeed
        } catch (e: ParserConfigurationException) {
            throw RSSParserException("Wrong parser configuration.", e)
        } catch (e: SAXException) {
            throw RSSParserException("SAX error occurred.", e)
        } catch (e: IOException) {
            throw RSSParserException("IOException during parsing input.", e)
        }
    }
}