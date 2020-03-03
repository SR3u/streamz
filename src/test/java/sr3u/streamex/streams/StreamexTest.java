package sr3u.streamex.streams;

import org.junit.Test;

import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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
    public void testReduce() {
    }

    @Test
    public void testReduce1() {
    }

    @Test
    public void collect() {
    }

    @Test
    public void testCollect() {
    }

    @Test
    public void min() {
    }

    @Test
    public void max() {
    }

    @Test
    public void count() {
    }

    @Test
    public void anyMatch() {
    }

    @Test
    public void allMatch() {
    }

    @Test
    public void noneMatch() {
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
    public void findAny() {
    }

    @Test
    public void empty() {
    }

    @Test
    public void of() {
    }

    @Test
    public void ofStream() {
    }

    @Test
    public void testOf() {
    }

    @Test
    public void iterate() {
    }

    @Test
    public void generate() {
    }

    @Test
    public void concat() {
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

        public void setaString(String aString) {
            this.aString = aString;
        }

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }

        public long getaLong() {
            return aLong;
        }

        public void setaLong(long aLong) {
            this.aLong = aLong;
        }

        public double getaDouble() {
            return aDouble;
        }

        public void setaDouble(double aDouble) {
            this.aDouble = aDouble;
        }
    }
}