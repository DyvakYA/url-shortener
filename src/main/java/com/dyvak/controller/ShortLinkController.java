package com.dyvak.controller;

import com.dyvak.service.ShortLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class ShortLinkController {

    private final static Logger log = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    private ShortLinkService shortLinkService;

    @RequestMapping("/{url}")
    public void sendRedirect(HttpServletResponse response, @PathVariable(value = "url") String url) throws IOException {
        String redirectionUrl = shortLinkService.findUrl(url);
        response.sendRedirect(redirectionUrl);
    }
}
