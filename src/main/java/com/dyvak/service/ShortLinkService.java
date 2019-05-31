package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import com.dyvak.exception.LinkNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ShortLinkService {

    @Autowired
    private LinkPool pool;

    public String findUrl(String url) {
        Optional<DynamicLink> optional = pool.getCorrectLink(url);
        if (optional.isPresent()) {
            DynamicLink link = optional.get();
            return link.getLongUrl();
        } else {
            throw new LinkNotFoundException();
        }
    }
}
