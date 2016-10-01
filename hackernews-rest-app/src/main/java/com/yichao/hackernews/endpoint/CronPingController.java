package com.yichao.hackernews.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@RestController
public class CronPingController {

    private static final Logger logger = LoggerFactory.getLogger(CronPingController.class);

    @GetMapping("/ping")
    public Response ping() {
        logger.info("Received a Ping at " + LocalDateTime.now());
        return Response.ok().build();
    }

}
