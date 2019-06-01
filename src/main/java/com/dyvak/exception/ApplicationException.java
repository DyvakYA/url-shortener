package com.dyvak.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final Throwable cause) {
        super(cause);
    }
}
