package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DynamicLinkService {

    @Autowired
    private DynamicLinkPool pool;

    @Autowired
    private LinkGenerator linkGenerator;

    public DynamicLink createDynamicLink(String url, String scheme, String serverName) {
        if (pool.checkIfExistByLongUrl(url)) {
            Optional<DynamicLink> optional = pool.getByLongUrl(url);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        String shortUrl = linkGenerator.generate(scheme, serverName);
        DynamicLink dynamicLink = DynamicLink.builder()
                .kind("urlshortener#url")
                .id(shortUrl)
                .longUrl(url)
                .build();
        pool.put(dynamicLink);
        return dynamicLink;
    }
}
