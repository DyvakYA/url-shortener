package com.dyvak.controller;

import com.dyvak.exception.NotValidShortLinkFormatException;
import com.dyvak.service.ShortLinkService;
import com.dyvak.validator.ShortLinkValidator;
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
public class ShortLinkController {

    private final static Logger log = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    private ShortLinkService shortLinkService;

    @Autowired
    private ShortLinkValidator urlValidator;

    @RequestMapping("/{uri}")
    public void sendRedirect(@PathVariable(value = "uri") String uri, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Redirection by short link");

        String url = request.getRequestURL().toString();
        log.info(url);
        if (urlValidator.isValid(uri)) {
            String redirectionUrl = shortLinkService.findUrl(url);
            response.sendRedirect(redirectionUrl);
        } else {
            throw new NotValidShortLinkFormatException("Wrong link format");
        }
    }
}
