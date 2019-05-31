package com.dyvak.service;

import com.dyvak.entity.DynamicLink;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class LinkPool {

    private Set<DynamicLink> pool = new HashSet<>();

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

    public Optional<DynamicLink> getCorrectLink(String url) {
        return pool.stream()
                .filter(l -> url.equals(l.getId()))
                .findFirst();
    }
}
