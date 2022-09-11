import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    void nullExceptionInConstructorParameters(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    void emptyListExceptionInConstructorParameters(){
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    void getHorsesTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("name" + i, i, i*5));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors : horses) {
            verify(hors).move();
        }

    }

    @Test
    void getWinnerTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            horses.add(new Horse("name" + i, i, i*5));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses.get(99), hippodrome.getWinner());
    }
}
