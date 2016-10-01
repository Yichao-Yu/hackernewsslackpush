package com.yichao.hackernews.rs;

import com.yichao.hackernews.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
@PropertySource("classpath:application.secret.properties")
public class SlackWebHookClient extends BaseRestClient {

    private final static Logger logger = LoggerFactory.getLogger(SlackWebHookClient.class);

    @Value("${slack_webhook_url}")
    private String SLACK_WEBHOOK_URL;

    @Override
    protected String getBaseUrl() {
        return SLACK_WEBHOOK_URL;
    }

    public Response postLink(final String link) {
        return getWebClient().post("{\"text\":\"<" + link + ">\",\"unfurl_links\": true}");
    }

    public Response postMessage(final SlackMessage message) {
        return getWebClient().post(message);
    }
}
