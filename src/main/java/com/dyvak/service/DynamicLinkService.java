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
        if (pool.checkIfExistByUrl(url)) {
            Optional<DynamicLink> optional = pool.getByUrl(url);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        String shortLink = linkGenerator.generate(scheme, serverName);
        DynamicLink link = DynamicLink.builder()
                .kind("urlshortener#url")
                .id(shortLink)
                .longUrl(url)
                .build();

        pool.put(link);
        return link;
    }
}
