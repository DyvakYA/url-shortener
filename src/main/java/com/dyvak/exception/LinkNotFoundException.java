package com.dyvak.exception;

public class LinkNotFoundException extends ApplicationException {

    public LinkNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LinkNotFoundException(final String message) {
        super(message);
    }

    public LinkNotFoundException(final Throwable cause) {
        super(cause);
    }
}
