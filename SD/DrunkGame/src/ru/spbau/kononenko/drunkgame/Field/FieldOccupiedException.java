package ru.spbau.kononenko.drunkgame.Field;

public class FieldOccupiedException extends RuntimeException {
	public FieldOccupiedException(String str) {
		super("Field is occupied: " + str);
	}
}
