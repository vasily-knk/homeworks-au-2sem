package ru.spbau.kononenko.drunkgame.common.actors;

public class MovingDeadActorException extends RuntimeException {
    public MovingDeadActorException(Actor actor) {
        super("Moving dead actor: " + actor.getClass().getSimpleName());
    }
}
