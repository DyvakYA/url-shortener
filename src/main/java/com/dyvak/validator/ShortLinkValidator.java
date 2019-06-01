package com.dyvak.validator;

import org.springframework.stereotype.Component;

@Component
public class ShortLinkValidator {

    public boolean isValid(String url) {
        if (url != null && !url.isBlank()) {
            if (url.length() == 6) {
                return true;
            }
        }
        return false;
    }
}
