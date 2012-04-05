package ru.spbau.kononenko.drunkgame;

public class Main {
    public static void main(String[] args) {
        RectField field = new RectField (15, 15);
        Coord drunkCoord = new Coord(3, 3);
        Coord pdCoord = new Coord(14, 3);
        
        new PoliceDepartment(field, field, field, pdCoord);
        Drunk drunk = new Drunk(field, field, drunkCoord, null, null);
        for (int i = 0; i < 10; ++i)
        {
            drunk.update();
            field.print();
        }
    }
}
