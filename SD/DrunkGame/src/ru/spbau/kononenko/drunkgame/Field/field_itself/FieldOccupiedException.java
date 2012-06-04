package ru.spbau.kononenko.drunkgame.field.field_itself;

public class FieldOccupiedException extends RuntimeException {
	public FieldOccupiedException(String str) {
		super("field_itself is occupied: " + str);
	}
}
