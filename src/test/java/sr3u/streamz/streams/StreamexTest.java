package sr3u.streamz.streams;

import org.junit.Test;
import sr3u.streamz.optionals.Optionalex;

import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StreamexTest {

    @Test
    public void filter() {
        String actual = createStream()
                .filter(item -> item.getAnInt() < 3)
                .map(Item::getaString)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2", actual);
    }

    @Test
    public void mapToInt() {
        String actual = createStream()
                .mapToInt(Item::getAnInt)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void mapToLong() {
        String actual = createStream()
                .mapToLong(Item::getaLong)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void mapToDouble() {
        String actual = createStream()
                .mapToDouble(Item::getaDouble)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals("0.4, 1.4, 2.4, 3.4, 4.4", actual);
    }

    @Test
    public void distinct() {
        String actual = Streamex.concat(createStream(), createStream())
                .map(Item::getaString)
                .distinct()
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void distinctByKey() {
        String actual = Streamex.concat(createStream(), createStream())
                .distinct(Item::getaString)
                .map(Item::getaString)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void sorted() {
        String actual = createStream()
                .mapToInt(Item::getAnInt)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void testSorted() {
        String actual = createStream(false)
                .map(Item::getaString)
                .sorted()
                .collect(Collectors.joining(", "));
        assertEquals("0, 1, 2, 3, 4", actual);
    }

    @Test
    public void anyMatch() {
        assertFalse(createStream()
                .anyMatch(item -> "".equals(item.getaString())));
        assertTrue(createStream()
                .anyMatch(item -> "2".equals(item.getaString())));
        assertTrue(createStream()
                .anyMatch(item -> "4".equals(item.getaString())));
    }

    @Test
    public void allMatch() {
        assertTrue(createStream()
                .map(item -> item.setaString("A"))
                .map(Item::getaString)
                .allMatch("A"::equals));
        assertFalse(createStream()
                .map(item -> item.setaString("B"))
                .map(Item::getaString)
                .allMatch("A"::equals));
        assertFalse(createStream()
                .map(Item::getaString)
                .allMatch("A"::equals));
    }

    @Test
    public void noneMatch() {
        assertTrue(createStream()
                .noneMatch(item -> "".equals(item.getaString())));
        assertFalse(createStream()
                .noneMatch(item -> "2".equals(item.getaString())));
        assertFalse(createStream()
                .noneMatch(item -> "4".equals(item.getaString())));
    }

    @Test
    public void findFirst() {
        String actual = createStream()
                .map(Item::getaString)
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
        Optionalex<Item> min = createStream()
                .min(Comparator.comparing(Item::getAnInt));
        int integer = min.mapToInt(Item::getAnInt)
                .orElseThrow(RuntimeException::new);
        assertEquals(0, integer);
    }

    @Test
    public void max() {
        Optionalex<Item> min = createStream()
                .max(Comparator.comparing(Item::getAnInt));
        int integer = min.mapToInt(Item::getAnInt)
                .orElseThrow(RuntimeException::new);
        assertEquals(4, integer);
    }

    @Test
    public void flatMap() {
    }

    @Test
    public void flatMapToInt() {
    }

    @Test
    public void flatMapToLong() {
    }

    @Test
    public void flatMapToDouble() {
    }

    @Test
    public void peek() {
    }

    @Test
    public void limit() {
    }

    @Test
    public void skip() {
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
    public void testToArray() {
    }

    @Test
    public void reduce() {
    }

    @Test
    public void findAny() {
    }

    @Test
    public void empty() {
    }

    @Test
    public void iterate() {
    }

    @Test
    public void generate() {
    }

    @Test
    public void iterator() {
    }

    @Test
    public void spliterator() {
    }

    @Test
    public void isParallel() {
    }

    @Test
    public void sequential() {
    }

    @Test
    public void parallel() {
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

    private Streamex<Item> createStream() {
        return createStream(true);
    }

    private Streamex<Item> createStream(boolean sorted) {
        Streamex<Item> streamex = Streamex.of(0, 1, 4, 3, 2)
                .map(i -> new Item("" + i, i, i, i + 0.4));
        if (sorted) {
            streamex = streamex.sorted(Comparator.comparing(Item::getAnInt));
        }
        return streamex;
    }


    public static class Item {
        private String aString;
        private int anInt;
        private long aLong;
        private double aDouble;

        public Item(String aString, int anInt, long aLong, double aDouble) {
            this.aString = aString;
            this.anInt = anInt;
            this.aLong = aLong;
            this.aDouble = aDouble;
        }

        public String getaString() {
            return aString;
        }

        public Item setaString(String aString) {
            this.aString = aString;
            return this;
        }

        public int getAnInt() {
            return anInt;
        }

        public Item setAnInt(int anInt) {
            this.anInt = anInt;
            return this;
        }

        public long getaLong() {
            return aLong;
        }

        public Item setaLong(long aLong) {
            this.aLong = aLong;
            return this;
        }

        public double getaDouble() {
            return aDouble;
        }

        public Item setaDouble(double aDouble) {
            this.aDouble = aDouble;
            return this;
        }
    }
}