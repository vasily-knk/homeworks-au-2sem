package ru.spbau.kononenko.drunkgame.common.field.field_itself;

public class FieldFreeException extends RuntimeException {
    public FieldFreeException(String str) {
        super("Can't remove object. field_itself is free: " + str);
    }
}
