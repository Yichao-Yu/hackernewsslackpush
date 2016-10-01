package com.yichao.hackernews.endpoint;

import com.yichao.hackernews.service.CronTriggerType;
import com.yichao.hackernews.service.HackerNewsService;
import com.yichao.hackernews.service.SlackWebHookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

import static com.yichao.hackernews.HackerNewsConstant.MAX_STORIES;

@RestController
public class HackerNewsPollController {

    private static final Logger logger = LoggerFactory.getLogger(HackerNewsPollController.class);

    private static final int ALL_NEW_STORIES = -1;

    @Autowired
    private HackerNewsService hackerNewsService;

    @Autowired
    private SlackWebHookService slackWebHookService;

    @GetMapping("/triggers/{triggerType}")
    public Response triggerCronJob(@PathVariable("triggerType") final String triggerType,
                                   @RequestParam(value = "count", required = false) final Integer count) {
        logger.info("Received a " + triggerType + " trigger at " + LocalDateTime.now());
        if (CronTriggerType.PULL_NEWS.name().equalsIgnoreCase(triggerType)) {
            final List<Integer> newsIds = hackerNewsService.getNews(count == null ? 1 : count);
            newsIds.stream().map(id -> hackerNewsService.getStory(id))
                    .forEach(item -> slackWebHookService.postHackerNewsToChannel(item));
        } else if (CronTriggerType.PULL_LATEST_NEWS.name().equalsIgnoreCase(triggerType)) {
            final List<Integer> newsIds = hackerNewsService.getNewsSinceLastRead(count == null ? MAX_STORIES : count);
            if (!newsIds.isEmpty()) {
                newsIds.stream().map(id -> hackerNewsService.getStory(id))
                        .forEach(item -> slackWebHookService.postHackerNewsToChannel(item));
            }
        } else {
            logger.info("Received an unsupported trigger type: " + triggerType);
        }
        return Response.ok().build();
    }

}
