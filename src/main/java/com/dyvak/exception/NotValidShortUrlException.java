package com.dyvak.exception;

public class NotValidShortUrlException extends ApplicationException {
    public NotValidShortUrlException(String message) {
        super(message);
    }
}
