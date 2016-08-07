package com.android.mucha.rssreader.rssloading

import android.net.Uri

/**
 * Represents parsed RSS Feed item.
 *
 * Only few basic elements are supported now (title, description, link and pubDate). To see all elements for item see:
 * [Elements of item](https://validator.w3.org/feed/docs/rss2.html#hrelementsOfLtitemgt)
 *
 * Use [Builder] or available constructor to create new instance.
 *
 * @author Patrik Mucha
 */
class RSSFeedItem(val mTitle: String?, val mDescription: String?, val mLink: Uri?, val mPubDate: Long) {

    /**
     * Creates new instance from builder.
     *
     * @param builder The builder instance.
     */
    private constructor(builder: Builder) : this(builder.mTitle, builder.mDescription, builder.mLink, builder.mPubDate)

    companion object {
        /**
         * Root element name.
         */
        val ELEMENT_ITEM_ROOT = "item"
        /**
         * Element name for "title".
         */
        val ELEMENT_TITLE = "title"
        /**
         * Element name for "description".
         */
        val ELEMENT_DESCRIPTION = "description"
        /**
         * Element name for "link".
         */
        val ELEMENT_LINK = "link"
        /**
         * Element name for "pubDate".
         */
        val ELEMENT_PUBDATE = "pubDate"

        /**
         * Set of valid child elements.
         */
        private val VALID_CHILD_ELEMENTS = setOf<String>(
                ELEMENT_TITLE, ELEMENT_DESCRIPTION, ELEMENT_LINK, ELEMENT_PUBDATE
        )

        /**
         * Returns whether the given [element] is valid child of [RSSFeedItem]'s element.
         *
         * @param element The identifier of element.
         * @return True if element is valid child, false otherwise (sibling or whatever).
         */
        fun isChildElementValid(element: String) = VALID_CHILD_ELEMENTS.contains(element)
    }

    /**
     * Builder to create [RSSFeedItem].
     */
    class Builder {
        var mTitle: String? = null
            private set
        var mDescription: String? = null
            private set
        var mLink: Uri? = null
            private set
        var mPubDate: Long = 0
            private set

        /**
         * Default constructor.
         */
        constructor()

        /**
         * Creates new instance from given [item].
         */
        constructor(item: RSSFeedItem) {
            mTitle = item.mTitle
            mDescription = item.mDescription
            mLink = item.mLink
            mPubDate = item.mPubDate
        }

        /**
         * Sets the item's title.
         *
         * @param title The title.
         * @return The builder.
         */
        fun title(title: String?) : Builder {
            mTitle = title
            return this
        }

        /**
         * Sets the item's description.
         *
         * @param description The description text.
         * @return The builder.
         */
        fun description(description: String?) : Builder {
            mDescription = description
            return this
        }

        /**
         * Sets the item's link.
         *
         * @param link The link.
         * @return The builder.
         */
        fun link(link: Uri?) : Builder {
            mLink = link
            return this
        }

        /**
         * Sets the item's pub date.
         *
         * @param pubDate The pub date.
         * @return The builder.
         */
        fun pubDate(pubDate: Long) : Builder {
            mPubDate = pubDate
            return this
        }

        /**
         * Builds the [RSSFeedItem] instance.
         *
         * @return The [RSSFeedItem] object.
         */
        fun build() = RSSFeedItem(this)
    }
}