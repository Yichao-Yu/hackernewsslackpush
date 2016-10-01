package com.yichao.hackernews.rs;

import com.yichao.hackernews.model.SlackMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.ws.rs.core.Response;

import static com.yichao.hackernews.HackerNewsConstant.HACKER_NEWS_USER_PAGE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SlackWebHookClient.class})
public class SlackWebHookClientIntTest {

    @Autowired
    private SlackWebHookClient client;

    @Test
    public void testPostLinkToChannel() throws Exception {
        client.postLink("https://github.com/Eloston/ungoogled-chromium|UnGoogled Chromium: Chromium with enhanced privacy, control and transparency");
    }

    @Test
    public void testPostMessageToChannel() throws Exception {
        final SlackMessage.SlackMessageAttachment attachment = new SlackMessage.SlackMessageAttachment(
                null,
                "jl",
                HACKER_NEWS_USER_PAGE + "jl",
                null,
                "Youtube",
                "https://www.youtube.com",
                1207886576L
        );
        Response response = client.postMessage(new SlackMessage("https://www.youtube.com", attachment));
        Assert.assertNotNull(response);
    }
}
