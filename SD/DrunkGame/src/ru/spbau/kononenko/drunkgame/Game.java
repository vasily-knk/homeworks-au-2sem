package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicObject;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.RectField;
import ru.spbau.kononenko.drunkgame.Portals.Bar;
import ru.spbau.kononenko.drunkgame.Portals.PoliceDept;
import ru.spbau.kononenko.drunkgame.Static.Pillar;
import ru.spbau.kononenko.drunkgame.Static.Streetlight;

import java.util.LinkedList;
import java.util.List;

public class Game implements DynamicControl {
    private final List<DynamicObject> objectsList = new LinkedList<DynamicObject>();
    private final List<DynamicObject> newObjectsList = new LinkedList<DynamicObject>();
    private final Field field;
    private int turn = 0;

    public Game () {
        field = new RectField(15, 15);
        objectsList.add(new Bar(field, new Coord(9, 0), this));
        field.setObject(new Coord(7, 7), new Pillar());
        
        PoliceDept dept = new PoliceDept(field, new Coord(14, 3), this); 
        objectsList.add(dept);

        Streetlight streetlight = new Streetlight(field, new Coord(10, 3), 3);
        dept.addSearcher(streetlight);
    }

    @Override
    public void add(DynamicObject object) {
        newObjectsList.add(object);
    }

    public void update() {
        for (DynamicObject object : objectsList) {
            if (object.isActive())
                object.update();
        }

        objectsList.addAll(newObjectsList);
        newObjectsList.clear();

        ++turn;
    }
    
    public void output() {
        System.out.println("Turn " + turn);
        field.draw();
    }

}
