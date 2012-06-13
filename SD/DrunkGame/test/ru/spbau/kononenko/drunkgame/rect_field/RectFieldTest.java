package ru.spbau.kononenko.drunkgame.rect_field;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.Field;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.FieldFreeException;
import ru.spbau.kononenko.drunkgame.common.field.field_itself.FieldOccupiedException;
import ru.spbau.kononenko.drunkgame.drunks.Bottle;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

public class RectFieldTest {
    static final int WIDTH = 15;
    static final int HEIGHT = 10;
    Field field;
    
    
    @Before
    public void setUp() throws Exception {
        field = spy(new RectField(WIDTH, HEIGHT));
    }
    
    @Test
    public void fieldOutside() {
        assertTrue(field.isInside(new Coord(0, 0)));
        assertTrue(field.isInside(new Coord(5, 5)));
        assertFalse(field.isInside(new Coord(-1, -1)));
        assertFalse(field.isInside(new Coord(16, 5)));
        assertFalse(field.isInside(new Coord(5, 11)));
        assertFalse(field.isInside(new Coord(16, 11)));
    }
    
    @Test
    public void fieldAdj() {
        List<Coord> adj = field.getAdjacent(new Coord(0, 0));
        assertEquals(adj.size(), 2);
        assertTrue(adj.contains(new Coord(1, 0)));
        assertTrue(adj.contains(new Coord(0, 1)));
        
        adj = field.getAdjacent(new Coord(5, 5));
        assertEquals(adj.size(), 4);
        assertTrue(adj.contains(new Coord(5, 4)));
        assertTrue(adj.contains(new Coord(5, 6)));
        assertTrue(adj.contains(new Coord(4, 5)));
        assertTrue(adj.contains(new Coord(6, 5)));
    }
    
    @Test
    public void fieldObjects() {
        Bottle bottle = new Bottle();

        assertEquals(field.getObject(new Coord(5, 5)), null);
        field.setObject(new Coord(5, 5), bottle);
        assertEquals(field.getObject(new Coord(5, 5)), bottle);
    }

    @Test (expected = FieldFreeException.class)
    public void fieldObjects1() {
        field.removeObject(new Coord(5, 5));
    }

    @Test (expected = FieldOccupiedException.class)
    public void fieldObjects2() {
        field.setObject(new Coord(5, 5), new Bottle());
        field.setObject(new Coord(5, 5), new Bottle());
    }
}
