package com.yichao.hackernews.service;

import com.yichao.hackernews.model.HackerNewsItem;
import com.yichao.hackernews.model.SlackMessage;
import com.yichao.hackernews.rs.SlackWebHookClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yichao.hackernews.HackerNewsConstant.HACKER_NEWS_USER_PAGE;

@Service
public class SlackWebHookService {

    private final static Logger logger = LoggerFactory.getLogger(SlackWebHookService.class);

    private final static String DELIMITER = "|";

    @Autowired
    private SlackWebHookClient client;

    public void postHackerNewsLinkToChannel(final HackerNewsItem item) {
        client.postLink(item.getUrl() + DELIMITER + item.getTitle());
    }

    public void postHackerNewsToChannel(final HackerNewsItem item) {
        final String authorUrl = HACKER_NEWS_USER_PAGE + item.getBy();
        final SlackMessage.SlackMessageAttachment attachment =
                new SlackMessage.SlackMessageAttachment(null, item.getBy(), authorUrl, null, item.getTitle(), item.getUrl(), item.getTime());
        client.postMessage(new SlackMessage(item.getTitle(), attachment));
    }
}
