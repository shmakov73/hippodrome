import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void nullInFirstParameterTest() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    void parametrizedConstructorTest(String arg){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 1, 2));
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    void nullInSecondParameterTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("S", -1, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void nullInThirdParameterTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("S", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String name = "string";
        Horse horse = new Horse(name, 1, 2);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeedTest() {
        int speed = 15;
        Horse horse = new Horse("horse", speed, 2);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        int distance = 15;
        Horse horse = new Horse("horse", 1, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void moveMethodTest(){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 1, 2);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.0, 5.0, 200.4, 599.99})
    void distanceValueTest(double value){
        int speed = 5;
        int distance = 17;
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("name", speed, distance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            assertEquals(distance+speed*value, horse.getDistance());
        }
    }
}