package sr3u.streamex.streams;

import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class StreamexSmokeTest {

    @Test
    public void smokeTest() {
        String expected = "FIVE, FOUR, ONE, THREE, TWO";

        String actual = Streamex.of("one", "three", "two", "three", "two", "four", "five")
                .map(String::toUpperCase)
                .sorted()
                .distinct()
                .collect(Collectors.joining(", "));
        assertEquals(expected, actual);
    }


}