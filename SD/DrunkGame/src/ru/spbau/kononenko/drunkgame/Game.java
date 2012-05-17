package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Logic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Logic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Drunks.Bar;
import ru.spbau.kononenko.drunkgame.Drunks.Pillar;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.RectField;
import ru.spbau.kononenko.drunkgame.Police.PoliceDept;
import ru.spbau.kononenko.drunkgame.Police.Streetlight;

import java.util.LinkedList;
import java.util.List;

public class Game implements DynamicControl {
    private final List<DynamicObject> objectsList = new LinkedList<DynamicObject>();
    private final List<DynamicObject> objectsToAdd = new LinkedList<DynamicObject>();

    private final Field field;
    private int turn = 0;

    public Game () {
        field = new RectField(15, 15);
        objectsList.add(new Bar(field, new Coord(9, 0), this, 20));
        field.setObject(new Coord(7, 7), new Pillar());
        
        PoliceDept dept = new PoliceDept(field, new Coord(14, 3), this);
        objectsList.add(dept);

        Streetlight streetlight = new Streetlight(field, new Coord(10, 3), 3);
        dept.addSearcher(streetlight);

        //Drunk drunk = new Drunk(field, new Coord(9, 3));
        //drunk.fallAsleep();
    }

    @Override
    public void add(DynamicObject object) {
        objectsToAdd.add(object);
    }

    public void update() {
        for (DynamicObject object : objectsList) {
            if (!object.isDead() && object.isActive())
                object.update();
        }

        objectsList.addAll(objectsToAdd);
        objectsToAdd.clear();

        ++turn;
    }
    
    public void output() {
        System.out.println("Turn " + turn);
        field.draw();
    }

}
