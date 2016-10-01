package com.yichao.hackernews.rs;

import com.yichao.hackernews.model.HackerNewsItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class HackerNewsClientIntTest {

    @InjectMocks
    private HackerNewsClient client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTopStories() throws Exception {
        List<Integer> result = client.getTopStories();
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetHackerNewsItem() throws Exception {
        HackerNewsItem item = client.getHackerNewsItem(12577787);
        Assert.assertNotNull(item);
    }

}
