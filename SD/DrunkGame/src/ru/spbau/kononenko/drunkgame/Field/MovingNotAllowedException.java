package ru.spbau.kononenko.drunkgame.Field;

public class MovingNotAllowedException extends RuntimeException {
    public MovingNotAllowedException(String message) {
        super("Moving not allowed: " + message);
    }
}
