package com.yichao.hackernews.rs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yichao.hackernews.model.HackerNewsItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static com.yichao.hackernews.HackerNewsConstant.*;

@Component
public class HackerNewsClient extends BaseRestClient {

    private final static Logger logger = LoggerFactory.getLogger(HackerNewsClient.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected String getBaseUrl() {
        return HACKER_NEWS_BASE_URL;
    }

    public List<Integer> getTopStories() {
        final Response response = getWebClient().path(HACKER_NEWS_TOP_STORIES).get();
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            logger.info("Getting Top Stories succeeds.");
            final String jsonStr = response.readEntity(String.class);
            try {
                return objectMapper.readValue(jsonStr,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
            } catch (IOException ex) {
                throw new WebApplicationException(ex);
            }
        } else throw new WebApplicationException(response);
    }

    public List<Integer> getNewStories() {
        final Response response = getWebClient().path(HACKER_NEWS_NEW_STORIES).get();
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            logger.info("Getting New Stories succeeds.");
            final String jsonStr = response.readEntity(String.class);
            try {
                return objectMapper.readValue(jsonStr,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
            } catch (IOException ex) {
                throw new WebApplicationException(ex);
            }
        } else throw new WebApplicationException(response);
    }

    public HackerNewsItem getHackerNewsItem(final Integer id) {
        final String path = String.format(HACKER_NEWS_ITEM, id);
        final Response response = getWebClient().path(path).get();
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            logger.info("Getting Item {} succeeds.", id);
            final String jsonStr = response.readEntity(String.class);
            try {
                return objectMapper.readValue(jsonStr, HackerNewsItem.class);
            } catch (IOException ex) {
                throw new WebApplicationException(ex);
            }
        } else throw new WebApplicationException(response);
    }
}
