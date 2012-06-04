package ru.spbau.kononenko.drunkgame.field.field_itself;

public class FieldOutOfRangeException extends RuntimeException {
	public FieldOutOfRangeException(String str) {
		super("Out of range: " + str);
	}
}
