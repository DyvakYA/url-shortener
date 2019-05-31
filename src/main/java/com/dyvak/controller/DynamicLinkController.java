package com.dyvak.controller;


import com.dyvak.entity.DynamicLink;
import com.dyvak.entity.LongUrl;
import com.dyvak.exception.NotProperlyRequestException;
import com.dyvak.service.DynamicLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urlshortener/v1/url")
public class DynamicLinkController {

    private final static Logger log = LoggerFactory.getLogger(DynamicLinkController.class);

    @Autowired
    private DynamicLinkService dynamicLinkService;

    @RequestMapping(method = RequestMethod.POST)
    public DynamicLink createDynamicLink(@RequestBody LongUrl longUrl) {
        if (longUrl != null) {
            log.info("Controller: Get dynamic link");
            log.info(longUrl.toString());
            // Invoke the appropriate service method and return
            String url = longUrl.getLongUrl();
            return dynamicLinkService.createDynamicLink(url);
        } else {
            throw new NotProperlyRequestException();
        }
    }
}
