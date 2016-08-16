# RSSReader-Kotlin-Rx-MVP
## About
Small Android application to read RSS Feeds.

## Few words to start
This project is mainly for learning purposes. I just wanted to learn/improve in new technologies and since it doesn't
 contain any secret information I decided to share it publicly. As the name of project says, it is written in Kotlin,
 Rx and uses MVP pattern. And the RSS reader is kind a simple app to start getting familiar with mentioned stuff.
 I'm sharing this project also because I'm not an expert, so I will appreciate any recommendations/tips or best
 practices from all of you. Thank you in advance. And of course, maybe it will help somebody else to get started :)

## Why Kotlin, Rx and MVP
I won't describe Kotlin, Rx and MVP deeply. I'll mention only few things I think are useful for me.

### Kotlin
Home page: https://kotlinlang.org

* statically typed programming language for the JVM, Android and the browser
* 100% interoperable with Java and libraries
* classes are immutable by default
* reduces boilerplate in your code

    _for example:_

    ```java
    class RSSFeedItem(val mTitle: String?, val mDescription: String?, val mLink: Uri?, val mPubDate: Long)
    ```

    _instead of:_

    ```java
    public class RSSFeedItem {
        public final String mTitle;
        public final String mDescription;
        public final Uri mLink;
        public final long mPubDate;

        public RSSFeedItem(String title, String description, Uri link, long pubDate) {
            mTitle = title;
            mDescription = description;
            mLink = link;
            mPubDate = pubDate;
        }

        [<Getters>]
    }
    ```

* smart casts

    ```kotlin
    fun demo(object: Any) {
        if (object is SomeObject) {
            object.doSomething() // object is automatically cast to SomeObject
        }
    }
    ```

* full Android Studio support
* and the best at the end...no more NullPointerExceptions :)

### Rx
TBD

### MVP (Model-View-Presenter)
I decided to use this pattern to separate logic from UI. Logic is now moved to Presenters which could be tested easily.
 There is also one disadvantage. You have to create two extra interfaces (for view and presenter) and one class for
 Presenter implementation basically for each screen.

Some useful links:
* <https://github.com/grandstaish/hello-mvp>
* <http://blog.bradcampbell.nz/mvp-presenters-that-survive-configuration-changes-part-1/>
