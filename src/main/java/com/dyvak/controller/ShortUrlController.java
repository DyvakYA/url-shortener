package com.dyvak.controller;

import com.dyvak.exception.NotValidShortUrlException;
import com.dyvak.service.ShortUrlService;
import com.dyvak.validator.ShortUrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class ShortUrlController {

    private final static Logger log = LoggerFactory.getLogger(ShortUrlController.class);

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private ShortUrlValidator urlValidator;

    @RequestMapping("/{uri}")
    public void sendRedirect(@PathVariable(value = "uri") String uri,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        String url = request.getRequestURL().toString();
        log.info("Redirection by short link : {}", url);
        if (urlValidator.isValid(uri)) {
            String redirectionUrl = shortUrlService.findUrl(url);
            response.sendRedirect(redirectionUrl);
        } else {
            throw new NotValidShortUrlException("Wrong link format");
        }
    }
}
