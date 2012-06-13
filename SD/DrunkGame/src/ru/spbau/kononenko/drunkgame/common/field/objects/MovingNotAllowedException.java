package ru.spbau.kononenko.drunkgame.common.field.objects;

public class MovingNotAllowedException extends RuntimeException {
    public MovingNotAllowedException(String message) {
        super("Moving not allowed: " + message);
    }
}
