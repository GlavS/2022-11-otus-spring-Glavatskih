package org.example.ingredient;

public class NotWashedException extends RuntimeException {
    public NotWashedException(String message) {
        super(message);
    }
}
