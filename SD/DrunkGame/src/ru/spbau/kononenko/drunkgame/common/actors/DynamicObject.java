package ru.spbau.kononenko.drunkgame.common.actors;

public interface DynamicObject {
	public void update();
	public boolean isActive();
    public boolean isDead();
}
