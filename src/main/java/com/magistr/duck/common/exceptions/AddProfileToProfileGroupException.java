package com.magistr.duck.common.exceptions;

public class AddProfileToProfileGroupException extends RuntimeException {

    public AddProfileToProfileGroupException() {
        super();
    }

    public AddProfileToProfileGroupException(final String message) {
        super(message);
    }

    public AddProfileToProfileGroupException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AddProfileToProfileGroupException(final Throwable cause) {
        super(cause);
    }

    protected AddProfileToProfileGroupException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
