package ru.bender.darts.api.exceptions;

/**
 * Created by bender on 24.10.16.
 */
public class DartsApiRuntimeException extends RuntimeException {
    public DartsApiRuntimeException(String message) {
        super(message);
    }
}
