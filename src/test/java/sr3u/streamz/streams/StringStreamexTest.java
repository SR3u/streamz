package sr3u.streamz.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringStreamexTest {

    @Test
    public void joined() {
        List<String> initial = Arrays.asList("1", "2", "3");
        assertEquals("123", Streamex.ofStream(initial.stream())
                .asStringStream()
                .joined());

        assertEquals("1,2,3", Streamex.ofStream(initial.stream())
                .asStringStream()
                .joined(","));

        assertEquals("prefix1,2,3suffix", Streamex.ofStream(initial.stream())
                .asStringStream()
                .joined(",", "prefix", "suffix"));

    }

    @Test
    public void streamConversions() {
        assertEquals("123", IntStreamex.of(1, 2, 3)
                .asStringStream()
                .joined());

        assertEquals("123", LongStreamex.of(1, 2, 3)
                .asStringStream()
                .joined());

        assertEquals("1.32.13.2", DoubleStreamex.of(1.3, 2.1, 3.2)
                .asStringStream()
                .joined());
    }
}