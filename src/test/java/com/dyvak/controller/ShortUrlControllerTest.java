package com.dyvak.controller;

import com.dyvak.entity.DynamicLink;
import com.dyvak.service.DynamicLinkService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShortUrlControllerTest extends AbstractControllerTest {

    private final static Logger log = LoggerFactory.getLogger(ShortUrlControllerTest.class);

    @Autowired
    private DynamicLinkService dynamicLinkService;

    private DynamicLink dynamicLinkWithHttp;
    private DynamicLink dynamicLinkWithHttps;

    @Before
    public void setUp() {
        super.setUp();
        dynamicLinkWithHttp = dynamicLinkService.createDynamicLink("http://google.com", "http", "localhost");
        dynamicLinkWithHttps = dynamicLinkService.createDynamicLink("https://google.com", "http", "localhost");
    }

    @Test
    public void sendShortLinkRequestForRedirecting_whenShortLinkIsGoodAndSchemeIsHttp() throws Exception {
        log.info(dynamicLinkWithHttp.toString());
        String uri = dynamicLinkWithHttp.getShortUrl();
        log.info(uri);
        mvc
                .perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(redirectedUrl(dynamicLinkWithHttp.getLongUrl()))
                .andExpect(status().isFound());
    }

    @Test
    public void sendShortLinkRequestForRedirecting_whenShortLinkIsGoodAndSchemeIsHttps() throws Exception {
        log.info(dynamicLinkWithHttps.toString());
        String uri = dynamicLinkWithHttps.getShortUrl();
        log.info(uri);
        mvc
                .perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(redirectedUrl(dynamicLinkWithHttps.getLongUrl()))
                .andExpect(status().isFound());
    }

    @Test
    public void sendShortLinkRequestForRedirecting_whenShortLinkIsBad() throws Exception {

        String uri = "/12345";
        mvc
                .perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }
}
