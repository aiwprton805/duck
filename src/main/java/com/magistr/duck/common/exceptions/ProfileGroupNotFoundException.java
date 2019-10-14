package com.magistr.duck.common.exceptions;

public class ProfileGroupNotFoundException extends RuntimeException {

    public ProfileGroupNotFoundException() {
        super();
    }

    public ProfileGroupNotFoundException(final String message) {
        super(message);
    }

    public ProfileGroupNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProfileGroupNotFoundException(final Throwable cause) {
        super(cause);
    }

    protected ProfileGroupNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
//class
