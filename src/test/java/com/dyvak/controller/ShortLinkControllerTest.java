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

public class ShortLinkControllerTest extends AbstractControllerTest {

    private final static Logger log = LoggerFactory.getLogger(ShortLinkControllerTest.class);

    @Autowired
    private DynamicLinkService dynamicLinkService;

    private DynamicLink dynamicLink;

    @Before
    public void setUp() {
        super.setUp();
        dynamicLink = dynamicLinkService.createDynamicLink("http://google.com", "http", "localhost");
    }

    @Test
    public void sendShortLinkRequestForRedirecting_whenShortLinkIsBad() throws Exception {
        log.info(dynamicLink.toString());
        String uri = dynamicLink.getShortUrl();
        log.info(uri);
        mvc
                .perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(redirectedUrl(dynamicLink.getLongUrl()))
                .andExpect(status().isFound());
    }

    @Test
    public void sendShortLinkRequestForRedirecting_whenShortLinkIsGood() throws Exception {

        String uri = "/12345";
        mvc
                .perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }
}
