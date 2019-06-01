package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DynamicLinkPool {

    private Set<DynamicLink> pool = Collections.synchronizedSet(new HashSet<>());

    public void put(DynamicLink link) {
        pool.add(link);
    }

    public void remove(DynamicLink link) {
        pool.remove(link);
    }

    public void clear() {
        pool.clear();
    }

    public boolean checkIfExist(DynamicLink link) {
        return link != null && pool.contains(link) ? true : false;
    }

    public boolean checkIfExistByUrl(String url) {
        Optional<DynamicLink> optional = pool.stream()
                .filter(d -> url.equals(d.getLongUrl()))
                .findAny();
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    public Optional<DynamicLink> getCorrectLink(String url) {
        return pool.stream()
                .filter(l -> url.equals(l.getShortUrl()))
                .findFirst();
    }

    public Optional<DynamicLink> getByUrl(String url) {
        Optional<DynamicLink> optional = pool.stream()
                .filter(d -> url.equals(d.getLongUrl()))
                .findAny();
        return optional;
    }

    public long size() {
        return pool.size();
    }
}
