package ru.spbau.kononenko.drunkgame.field.objects;

public class MovingNotAllowedException extends RuntimeException {
    public MovingNotAllowedException(String message) {
        super("Moving not allowed: " + message);
    }
}
