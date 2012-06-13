package ru.spbau.kononenko.drunkgame.police.arrestable;

import ru.spbau.kononenko.drunkgame.common.actors.SelfAware;
import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObject;
import ru.spbau.kononenko.drunkgame.police.policeman.Policeman;

public interface Arrestable extends FieldObject, SelfAware {
    void arrest(Policeman policeman);
}
