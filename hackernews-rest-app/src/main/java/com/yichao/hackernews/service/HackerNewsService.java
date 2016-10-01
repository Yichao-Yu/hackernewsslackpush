package com.yichao.hackernews.service;

import com.yichao.hackernews.model.HackerNewsItem;
import com.yichao.hackernews.rs.HackerNewsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.yichao.hackernews.HackerNewsConstant.MAX_STORIES;

@Service
public class HackerNewsService {

    private final static Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    private int lastReadStoryId = -1;

    @Autowired
    private HackerNewsClient client;

    @Autowired
    private FirebaseService firebaseService;

    public List<Integer> getNews(final int count) {
        logger.info("Try to pull {} news", count);
        final List<Integer> newsIds = client.getNewStories();
        if (count < 0 || newsIds.size() <= count && count <= MAX_STORIES) {
            return newsIds;
        }
        return newsIds.subList(0, count > MAX_STORIES ? MAX_STORIES : count);
    }

    public List<Integer> getNewsSinceLastRead(final int count) {
        final List<Integer> newsIds = getNews(count);
        final int storyCount = newsIds.size();
        logger.info("Last read story id {}", lastReadStoryId);
        if (storyCount == 0) {
            // no new news
            logger.info("no new news");
            return Collections.EMPTY_LIST;
        }
        if (lastReadStoryId == -1) {
            lastReadStoryId = newsIds.get(0);
            return newsIds;
        }
        // find the index of last read news
        List<Integer> copyNewsIds = new ArrayList<>(newsIds.size());
        newsIds.stream().forEach(copyNewsIds::add);
        Collections.sort(copyNewsIds);
        final int index = Collections.binarySearch(copyNewsIds, lastReadStoryId);
        if (index == storyCount - 1) {
            // no new news
            logger.info("no new news");
            return Collections.EMPTY_LIST;
        }
        lastReadStoryId = copyNewsIds.get(storyCount - 1);
        if (index < 0) {
            return newsIds;
        }
        return copyNewsIds.subList(0, storyCount - index - 1);
    }

    public HackerNewsItem getStory(final Integer storyId) {
        return client.getHackerNewsItem(storyId);
    }

    public boolean loadState() {
        logger.info("Starting application.. Load previous state");
        return true;
    }

    public boolean saveState() {
        logger.info("Shutting down.. Save current state");
        return true;
    }
}
