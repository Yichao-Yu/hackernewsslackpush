package com.yichao.hackernews.service;

import com.yichao.hackernews.rs.HackerNewsClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class HackerNewsServiceUnitTest {

    private final static Logger logger = LoggerFactory.getLogger(HackerNewsServiceUnitTest.class);

    @InjectMocks
    private HackerNewsService hackerNewsService;

    @Mock
    private HackerNewsClient mockHackerNewsClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNewsSinceLastRead() {
        List<Integer> storyIds = Arrays.asList(new Integer[]{12602583, 12602570, 12602540, 12602535});
        Mockito.doReturn(storyIds).doReturn(storyIds).when(mockHackerNewsClient).getNewStories();
        List<Integer> result1 = hackerNewsService.getNewsSinceLastRead(10);
        Assert.assertNotNull(result1);

        List<Integer> result2 = hackerNewsService.getNewsSinceLastRead(10);
        Assert.assertNotNull(result2);
        Assert.assertTrue(result2.isEmpty());
    }

    @Test
    public void testGetNewsSinceLastReadHasNew() {
        List<Integer> storyIds = Arrays.asList(12602583, 12602570, 12602540, 12602535);
        List<Integer> newStoryIds = Arrays.asList(12602590, 12602583, 12602570, 12602540, 12602535);
        Mockito.doReturn(storyIds).doReturn(newStoryIds).when(mockHackerNewsClient).getNewStories();
        List<Integer> result1 = hackerNewsService.getNewsSinceLastRead(10);
        Assert.assertNotNull(result1);
        Assert.assertTrue(4 == result1.size());

        List<Integer> result2 = hackerNewsService.getNewsSinceLastRead(10);
        Assert.assertNotNull(result2);
        Assert.assertTrue(1 == result2.size());
    }

    @Test
    public void testGetNewsSinceLastReadPullLess() {
        List<Integer> storyIds = Arrays.asList(12602583, 12602570, 12602540, 12602535);
        Mockito.doReturn(storyIds).when(mockHackerNewsClient).getNewStories();
        List<Integer> result1 = hackerNewsService.getNewsSinceLastRead(2);
        Assert.assertNotNull(result1);
        Assert.assertTrue(2 == result1.size());
        Assert.assertTrue(12602583 == result1.get(0));
        Assert.assertTrue(12602570 == result1.get(1));

    }
}
