package ru.otus.glavs.service.processor;

public final class Answer {
    private final int variant;
    private final boolean isCorrect;

    public Answer(int variant, boolean isCorrect) {
        this.variant = variant;
        this.isCorrect = isCorrect;
    }

    public int getVariant() {
        return variant;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
