package com.android.mucha.rssreader.rssloading

import android.net.Uri
import com.android.mucha.rssreader.utils.DateUtils
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

/**
 * Extension class of [DefaultHandler] which provides parsing of RSS Xml input and stores the results into an
 * [RSSFeed] instance.
 *
 * @author Patrik Mucha
 */
class RSSXmlHandler : DefaultHandler() {

    /**
     * Holds the parsed results.
     */
    val rssFeed = RSSFeed()

    private var rssFeedItemBuilder: RSSFeedItem.Builder? = null
    private var contentBuffer: StringBuilder? = null

    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        // another item in an item ?? You really don't want it
        if (rssFeedItemBuilder == null && RSSFeedItem.ELEMENT_ITEM_ROOT.equals(qName)) {
            rssFeedItemBuilder = RSSFeedItem.Builder()
        } else if (qName != null && RSSFeedItem.isChildElementValid(qName)) {
            contentBuffer = StringBuilder()
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (contentBuffer != null) {
            if (qName != null && RSSFeedItem.isChildElementValid(qName)) {
                setFeedItemElement(qName, contentBuffer.toString())
            }
            contentBuffer = null
        } else if (RSSFeedItem.ELEMENT_ITEM_ROOT.equals(qName)) {
            if (rssFeedItemBuilder != null) {
                rssFeed.mFeedItems.add(rssFeedItemBuilder!!.build())
                rssFeedItemBuilder = null
            }
        }
    }

    /**
     * Sets the value for item's element.
     *
     * @param qName The element name.
     * @param value The value of element.
     */
    fun setFeedItemElement(qName: String, value: String) {
        when (qName) {
            RSSFeedItem.ELEMENT_TITLE -> rssFeedItemBuilder?.title(value)
            RSSFeedItem.ELEMENT_DESCRIPTION -> rssFeedItemBuilder?.description(value)
            RSSFeedItem.ELEMENT_LINK -> rssFeedItemBuilder?.link(Uri.parse(value))
            RSSFeedItem.ELEMENT_PUBDATE -> rssFeedItemBuilder?.pubDate(DateUtils.parseDateFromRFC822(value))
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        contentBuffer?.append(ch, start, length)
    }
}