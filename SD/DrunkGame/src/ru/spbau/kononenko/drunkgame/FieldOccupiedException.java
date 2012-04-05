package ru.spbau.kononenko.drunkgame;

class FieldOccupiedException extends RuntimeException {
	public FieldOccupiedException(String str) {
		super("Occupied: " + str);
	}
}
