package ru.spbau.kononenko.drunkgame.Field;

public class FieldOutOfRangeException extends RuntimeException {
	public FieldOutOfRangeException(String str) {
		super("Out of range: " + str);
	}
}
