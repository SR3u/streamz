package sr3u.streamz.streams;

import org.junit.Test;
import sr3u.streamz.optionals.Optionalex;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    //
    // Syntax sugar tests
    //

    public static final String EXPECTED_ALL_ORDERED = "0, 1, 2, 3, 4";

    @Test
    public void filter() {
        String actual = createStream()
                .filter(item -> Integer.parseInt(item) < 3)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2", actual);
    }

    @Test
    public void mapToInt() {
        String actual = createStream()
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void mapToLong() {
        String actual = createStream()
                .mapToLong(Long::parseLong)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void mapToDouble() {
        String actual = createStream()
                .mapToDouble(Double::parseDouble)
                .map(d -> d + 0.4)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals("0.4, 1.4, 2.4, 3.4, 4.4", actual);
    }

    @Test
    public void distinct() {
        String actual = StringStreamex.concat(createStream(), createStream())
                .distinct()
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void distinctByKey() {
        String actual = StringStreamex.concat(createStream(), createStream())
                .distinct()
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void sorted() {
        String actual = createStream()
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void testSorted() {
        String actual = createStream(false)
                .sorted()
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void findFirst() {
        String actual = createStream()
                .findFirst()
                .orElse("ERROR");
        assertEquals("0", actual);
    }

    @Test
    public void collect() {
        String expected = "FIVE, FOUR, ONE, THREE, TWO";

        String actual = Streamex.of("one", "three", "two", "three", "two", "four", "five")
                .map(String::toUpperCase)
                .sorted()
                .distinct()
                .collect(Collectors.joining(", "));
        assertEquals(expected, actual);
    }

    @Test
    public void count() {
        assertEquals(5, createStream().count());
    }

    @Test
    public void min() {
        Optionalex<String> min = createStream()
                .min(Comparator.comparing(Integer::parseInt));
        int integer = min.mapToInt(Integer::parseInt)
                .orElseThrow();
        assertEquals(0, integer);
    }

    @Test
    public void max() {
        Optionalex<String> min = createStream().max();
        int integer = min.mapToInt(Integer::parseInt)
                .orElseThrow();
        assertEquals(4, integer);
    }

    @Test
    public void empty() {
        assertEquals(0, Streamex.empty().count());
    }

    @Test
    public void reduce() {
        String item = createStream()
                .reduce((a, b) -> b).orElse(null);
        assertEquals("4", item);
    }

    @Test
    public void skipAndLimit() {
        List<String> collect = createStream()
                .skip(2)
                .limit(1)
                .collect(Collectors.toList());
        assertEquals(1, collect.size());
        assertEquals("2", collect.get(0));
    }

    @Test
    public void parallelAndSequential() {
        assertTrue(createStream().parallel().isParallel());
        assertFalse(createStream().sequential().isParallel());
    }

    @Test
    public void peek() {
    }

    @Test
    public void forEach() {
    }

    @Test
    public void forEachOrdered() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void findAny() {
    }

    @Test
    public void iterate() {
    }

    @Test
    public void generate() {
    }

    @Test
    public void unordered() {
    }

    @Test
    public void onClose() {
    }

    @Test
    public void close() {
    }

    private StringStreamex createStream() {
        return createStream(true);
    }

    private StringStreamex createStream(boolean sorted) {
        StringStreamex streamex = StringStreamex.of("0", "1", "4", "3", "2");
        if (sorted) {
            streamex = streamex.sorted();
        }
        return streamex;
    }

}