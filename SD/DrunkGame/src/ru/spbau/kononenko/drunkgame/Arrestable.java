package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Walking.Policeman;

public interface Arrestable extends SelfAwareFieldObject {
    void arrest(Policeman policeman);
}
