package com.android.mucha.rssreader.database.dao;

import java.io.IOException;
import java.util.List;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.mucha.rssreader.database.AppDatabase;
import com.android.mucha.rssreader.database.model.RSSFeedModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for {@link RSSFeedModelDao}.
 *
 * @author Patrik Mucha
 */
@RunWith(AndroidJUnit4.class)
public class RSSFeedModelDaoTest {

    private RSSFeedModelDao mDao;
    private AppDatabase mDatabase;

    @Before
    public void createDb() {
        final Context context = InstrumentationRegistry.getTargetContext();
        mDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDatabase.getRssFeedModelDao();
    }

    @After
    public void closeDb() throws IOException {
        mDatabase.close();
    }

    @Test
    public void addSingleRecordAndCheckListSize() throws Exception {
        RSSFeedModel rssFeed = new RSSFeedModel();
        rssFeed.setMName("My Feed");
        rssFeed.setMUri("http://some.uri");
        mDao.insertAll(rssFeed);

        final List<RSSFeedModel> feeds = mDao.getAllFeeds();
        Assert.assertEquals("There should be only one record in the table.", feeds.size(), 1);
    }
}