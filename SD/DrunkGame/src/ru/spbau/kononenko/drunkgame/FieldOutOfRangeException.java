package ru.spbau.kononenko.drunkgame;

class FieldOutOfRangeException extends RuntimeException {
	public FieldOutOfRangeException(String str) {
		super("Out of range:" + str);
	}
}
