package com.dyvak.controller;


import com.dyvak.entity.DynamicLink;
import com.dyvak.entity.LongUrl;
import com.dyvak.exception.BadRequestException;
import com.dyvak.exception.NotValidLongUrlException;
import com.dyvak.service.DynamicLinkService;
import com.dyvak.validator.LongUrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/urlshortener/v1/url")
public class DynamicLinkController {

    private final static Logger log = LoggerFactory.getLogger(DynamicLinkController.class);

    @Autowired
    private DynamicLinkService dynamicLinkService;

    @Autowired
    private LongUrlValidator longUrlValidator;

    @RequestMapping(method = RequestMethod.POST)
    public DynamicLink createDynamicLink(@RequestBody LongUrl longUrl, HttpServletRequest request) {
        if (longUrl != null && longUrl.getLongUrl() != null) {
            log.info("Get dynamic link");
            log.info(longUrl.toString());

            String url = longUrl.getLongUrl();
            String scheme = request.getScheme();
            String serverName = request.getServerName();

            log.info("Scheme : {}, Server name : ()", scheme, serverName);
            if (!url.isBlank() && longUrlValidator.isValid(url)) {
                // Invoke the appropriate service method and return created DynamicLink
                return dynamicLinkService.createDynamicLink(url, scheme, serverName);
            } else {
                throw new NotValidLongUrlException("Url not valid");
            }
        } else {
            throw new BadRequestException("Not proper request");
        }
    }
}
