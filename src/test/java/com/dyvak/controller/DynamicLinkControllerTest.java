package com.dyvak.controller;

import com.dyvak.entity.DynamicLink;
import com.dyvak.entity.LongUrl;
import com.dyvak.exception.ErrorDetails;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class DynamicLinkControllerTest extends AbstractControllerTest {

    private final static Logger log = LoggerFactory.getLogger(DynamicLinkControllerTest.class);

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createDynamicLinkTest_whenUrlIsBad() throws Exception {
        String uri = "/urlshortener/v1/url";
        LongUrl longUrl = new LongUrl();
        longUrl.setLongUrl("3");

        String inputJson = super.mapToJson(longUrl);
        log.info(inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String errorMessage = mvcResult.getResponse().getErrorMessage();
        log.info(String.valueOf(status));
        log.info(errorMessage);
        // assertEquals(500, status);
        String content = mvcResult.getResponse().getContentAsString();
        ErrorDetails response = super.mapFromJson(content, ErrorDetails.class);
        log.info(response.toString());
        assertEquals("Url not valid", response.getMessage());
    }

    @Test
    public void createDynamicLinkTest_whenUrlIsGood() throws Exception {
        String uri = "/urlshortener/v1/url";
        LongUrl product = new LongUrl();
        product.setLongUrl("http://google.com");

        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        DynamicLink response = super.mapFromJson(result, DynamicLink.class);
        log.info(response.toString());
        assertEquals("urlshortener#url", response.getKind());
    }
}
