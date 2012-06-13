package ru.spbau.kononenko.drunkgame.common.actors;

import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.objects.SelfAwareFieldObject;

public abstract class Actor extends SelfAwareFieldObject implements DynamicObject {
    private boolean dead = false;

    public Actor(Field field, Coord coord) {
        super (field, coord);
    }

    @Override
    protected void moveTo(Coord c) {
        if (isDead())
            throw new MovingDeadActorException(this);
        super.moveTo(c);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    public void kill() {
        remove();
        dead = true;
    }
	
}
