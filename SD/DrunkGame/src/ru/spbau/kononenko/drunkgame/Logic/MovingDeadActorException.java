package ru.spbau.kononenko.drunkgame.Logic;

public class MovingDeadActorException extends RuntimeException {
    public MovingDeadActorException(Actor actor) {
        super("Moving dead actor: " + actor.getClass().getSimpleName());
    }
}
