package com.yichao.hackernews.service.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Map;

@Controller
public class AuthorizationController {

    private final static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private static String secret = "0d82f3cac38b0bc8d623f7b2ba2df3c3";
    private static String token = "SU3PRUKd4ZYLH3sw7YIpZApzcZAkYleuYym-lDNCrtAiCiBnt6TCCgg4JizkklYYnTU4hsWPby8YEiFtMtVyicd6R4pAg2oGnPIXaIGE-bp0IE6RNR3RVUpOAAMDhKAtSEXgABAYKD";

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @PUT
    @RequestMapping("/token")
    @ResponseBody
    public Response updateToken(@RequestBody String body) throws Exception {
        Map<String, String> bodyMap = objectMapper.readValue(body, Map.class);
        String appid = bodyMap.get("appid");

        WebClient webClient = WebClient.create("https://api.weixin.qq.com/cgi-bin");
        webClient.accept(MediaType.APPLICATION_JSON)
                .path("/token")
                .query("grant_type", "client_credential")
                .query("appid", appid)
                .query("secret", secret);
        logger.info(webClient.getCurrentURI().toASCIIString());
        Response response = webClient.get();
        Map<String, String> responseMap = objectMapper.readValue(response.readEntity(String.class), Map.class);
        if (responseMap.containsKey("access_token")) {
            token = responseMap.get("access_token");
            logger.info("token is updated " + token);
            return Response.ok().build();
        } else {
            logger.error("get access token failed with error {}", responseMap.get("errcode"));
            return Response.status(400).entity(String.valueOf(responseMap.get("errcode")) + " " + responseMap.get("errmsg")).build();
        }
    }

    @GET
    @RequestMapping("/auth")
    @ResponseBody
    public String auth(@RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("echostr") String echoString) {
        String[] tmpArr = new String[]{token, timestamp, nonce};
        Arrays.sort(tmpArr);

//        String tmpSignature = new String(DigestUtils.sha1Hex(String.join("", tmpArr)).getBytes(), Charset.defaultCharset());
//        if (signature.equals(tmpSignature)) {
//            logger.info("Auth success");
//            return echoString;
//        }
//        logger.error("Auth fail");
        return "FAILED";
    }

}