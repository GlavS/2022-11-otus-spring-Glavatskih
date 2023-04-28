package org.example.ingredient;

public class NotCookedException extends RuntimeException {
    public NotCookedException(String message) {
        super(message);
    }
}
