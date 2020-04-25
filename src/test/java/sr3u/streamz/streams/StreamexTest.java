package sr3u.streamz.streams;

import org.junit.Test;
import sr3u.streamz.optionals.Optionalex;
import sr3u.streamz.test.Item;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StreamexTest {

    public static final String EXPECTED_ALL_ORDERED = "0, 1, 2, 3, 4";

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
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void mapToLong() {
        String actual = createStream()
                .mapToLong(Item::getaLong)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
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
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void distinctByKey() {
        String actual = Streamex.concat(createStream(), createStream())
                .distinct(Item::getaString)
                .map(Item::getaString)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void sorted() {
        String actual = createStream()
                .mapToInt(Item::getAnInt)
                .mapToObj(i -> "" + i)
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
    }

    @Test
    public void testSorted() {
        String actual = createStream(false)
                .map(Item::getaString)
                .sorted()
                .collect(Collectors.joining(", "));
        assertEquals(EXPECTED_ALL_ORDERED, actual);
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
                .orElseThrow();
        assertEquals(0, integer);
    }

    @Test
    public void max() {
        Optionalex<Item> min = createStream()
                .max(Comparator.comparing(Item::getAnInt));
        int integer = min.mapToInt(Item::getAnInt)
                .orElseThrow();
        assertEquals(4, integer);
    }

    @Test
    public void flatMap() {
        List<Integer> source = Streamex.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
        int[] destination = Streamex.of(source)
                .flatMap(list -> Streamex.of(list.toArray()))
                .mapToInt(i -> (Integer) i)
                .toArray();
        Integer expected = source.get(3);
        Integer actual = destination[3];
        assertEquals(expected, actual);
    }

    @Test
    public void flatMapToInt() {
        List<Integer> source = Streamex.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
        int[] destination = Streamex.of(source)
                .flatMapToInt(list -> {
                    int[] ints = new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        ints[i] = list.get(i);
                    }
                    return IntStreamex.of(ints);
                })
                .toArray();
        Integer expected = source.get(3);
        Integer actual = destination[3];
        assertEquals(expected, actual);
    }

    @Test
    public void flatMapToLong() {
        List<Long> source = Streamex.of(1, 2, 3, 4, 5, 6)
                .mapToLong(Integer::longValue)
                .boxed()
                .collect(Collectors.toList());
        long[] destination = Streamex.of(source)
                .flatMapToLong(list -> {
                    long[] longs = new long[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        longs[i] = list.get(i);
                    }
                    return LongStreamex.of(longs);
                })
                .toArray();
        Long expected = source.get(3);
        Long actual = destination[3];
        assertEquals(expected, actual);
    }

    @Test
    public void flatMapToDouble() {
        List<Double> source = Streamex.ofCollection(Arrays.asList(1.1, 2, 3.2, 4, 5, 6))
                .mapToDouble(Number::doubleValue)
                .boxed()
                .collect(Collectors.toList());
        double[] destination = Streamex.of(source)
                .flatMapToDouble(list -> {
                    double[] doubles = new double[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        doubles[i] = list.get(i);
                    }
                    return DoubleStreamex.of(doubles);
                })
                .toArray();
        Double expected = source.get(3);
        Double actual = destination[3];
        assertEquals(expected, actual);
    }

    @Test
    public void empty() {
        assertEquals(0, Streamex.empty().count());
    }

    @Test
    public void reduce() {
        Item item = createStream()
                .reduce((a, b) -> b).orElse(null);
        assertEquals(4, item.getAnInt());
    }

    @Test
    public void skipAndLimit() {
        List<Item> collect = createStream()
                .skip(2)
                .limit(1)
                .collect(Collectors.toList());
        assertEquals(1, collect.size());
        assertEquals(2, collect.get(0).getAnInt());
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


}