package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DynamicLinkService {

    @Autowired
    private LinkPool pool;

    public DynamicLink createDynamicLink(String url) {

        String shortLink = createShortLink();

        DynamicLink link = DynamicLink.builder()
                .kind("urlshortener#url")
                .id(shortLink)
                .longUrl(url)
                .build();

        pool.put(link);
        return link;
    }

    private String createShortLink() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
