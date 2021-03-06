package com.timostaudinger.dailydose.common.exception;

abstract public class DailyDoseException extends RuntimeException {
    public DailyDoseException(String message) {
        super(message);
    }

    public DailyDoseException(String message, Exception cause) {
        super(message, cause);
    }
}
