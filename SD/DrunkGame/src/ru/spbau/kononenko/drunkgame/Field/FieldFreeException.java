package ru.spbau.kononenko.drunkgame.Field;

public class FieldFreeException extends RuntimeException {
    public FieldFreeException(String str) {
        super("Can't remove object. Field is free: " + str);
    }
}
