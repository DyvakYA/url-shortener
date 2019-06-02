package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import com.dyvak.exception.ShortUrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortUrlService {

    @Autowired
    private DynamicLinkPool pool;

    public String findUrl(String url) {
        Optional<DynamicLink> optional = pool.getByShortUrl(url);
        if (optional.isPresent()) {
            DynamicLink link = optional.get();
            return link.getLongUrl();
        } else {
            throw new ShortUrlNotFoundException("Link not found in our database");
        }
    }
}
