package sr3u.streamz.streams;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void printEach() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        LongStreamex.of(1, 2, 3)
                .asStringStream()
                .printEach(new PrintStream(outStream));
        assertEquals("123", outStream.toString());
    }

    @Test
    public void printlnEach() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        LongStreamex.of(1, 2, 3)
                .asStringStream()
                .printlnEach(new PrintStream(outStream));
        String ls = System.lineSeparator();
        assertEquals("1" + ls + "2" + ls + "3" + ls, outStream.toString());
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