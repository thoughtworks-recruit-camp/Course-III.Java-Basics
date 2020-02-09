package direction;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {
    @Test
    void uniqueDirection() {
        HashSet<Direction> uniqueSet = new HashSet<>();
        for (int i = 1; i < 2; i++) {
            uniqueSet.add(new DirectionN());
            uniqueSet.add(new DirectionE());
            uniqueSet.add(new DirectionS());
            uniqueSet.add(new DirectionW());
        }
        assertEquals(uniqueSet.size(), 4);
        assertTrue(uniqueSet.contains(new DirectionN()));
        assertTrue(uniqueSet.contains(new DirectionE()));
        assertTrue(uniqueSet.contains(new DirectionS()));
        assertTrue(uniqueSet.contains(new DirectionW()));
    }

    @Test
    void turnLeft() {
        assertEquals(new DirectionN().turnLeft(), new DirectionW());
        assertEquals(new DirectionW().turnLeft(), new DirectionS());
        assertEquals(new DirectionS().turnLeft(), new DirectionE());
        assertEquals(new DirectionE().turnLeft(), new DirectionN());
    }

    @Test
    void turnRight() {
        assertEquals(new DirectionN().turnRight(), new DirectionE());
        assertEquals(new DirectionE().turnRight(), new DirectionS());
        assertEquals(new DirectionS().turnRight(), new DirectionW());
        assertEquals(new DirectionW().turnRight(), new DirectionN());
    }

    @Test
    void turnAround() {
        assertEquals(new DirectionN().turnRight().turnRight().turnRight().turnRight(),
                new DirectionN());
    }
}