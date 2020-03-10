package sr3u.streamz.streams;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntStreamexTest {

    @Test
    public void empty() {
        assertEquals(0, IntStreamex.empty().count());
    }

    @Test
    public void concatAndDistinct() {
        assertEquals(12, IntStreamex.concat(createStream(), createStream())
                .count());
        assertEquals(6, IntStreamex.concat(createStream(), createStream())
                .distinct()
                .count());
    }

    @Test
    public void sortedAndFindFirst() {
        assertEquals(0, createStream().sorted().findFirst().orElseThrow());
    }

    @Test
    public void skipAndLimit() {
        int[] ints = createStream()
                .skip(2)
                .limit(1)
                .toArray();
        assertEquals(1, ints.length);
        assertEquals(2, ints[0]);
    }

    @Test
    public void skipAndAndFindAny() {
        assertEquals(2, createStream()
                .skip(2)
                .limit(1)
                .findAny()
                .orElseThrow());
    }

    @Test
    public void filter() {
        assertEquals(3, createStream()
                .filter(i -> i == 3)
                .findFirst()
                .orElseThrow());
    }

    @Test
    public void minMax() {
        assertEquals(0, createStream().min().orElseThrow());
        assertEquals(5, createStream().max().orElseThrow());
    }

    @Test
    public void anyMatch() {
        assertTrue(createStream().anyMatch(i -> i == 4));
        assertFalse(createStream().anyMatch(i -> i == 100));
    }

    @Test
    public void noneMatch() {
        assertFalse(createStream().noneMatch(i -> i == 4));
        assertTrue(createStream().noneMatch(i -> i == 100));
    }

    @Test
    public void allMatch() {
        assertFalse(createStream().allMatch(i -> i == 4));
        assertTrue(createStream(1, 1, 1).allMatch(i -> i == 1));
    }

    @Test
    public void asOtherStreams() {
        assertEquals(4, createStream()
                .asLongStream()
                .asDoubleStream()
                .mapToInt(i -> (int) i)
                .asDoubleStream()
                .boxed()
                .mapToDouble(i -> i)
                .mapToInt(i -> (int) i)
                .asLongStream()
                .boxed()
                .mapToLong(i -> i)
                .mapToInt(i -> (int) i)
                .boxed()
                .mapToInt(i -> i)
                .skip(3)
                .limit(1)
                .findFirst().orElseThrow());
    }

    @Test
    public void sum() {
        assertEquals(15, createStream().sum());
    }

    @Test
    public void average() {
        double average = createStream().average().orElseThrow();
        assertEquals(2.5, average, 1e-10);
    }

    @Test
    public void parallelAndSequential() {
        assertTrue(createStream().parallel().isParallel());
        assertFalse(createStream().sequential().isParallel());
    }

    @Test
    public void range() {
        assertEquals(0, IntStreamex.range(0, 5).min().orElseThrow());
        assertEquals(4, IntStreamex.range(0, 5).max().orElseThrow());
        assertEquals(3, IntStreamex.range(0, 5).skip(3).limit(1).findFirst().orElseThrow());
    }

    @Test
    public void rangeClosed() {
        assertEquals(0, IntStreamex.rangeClosed(0, 5).min().orElseThrow());
        assertEquals(5, IntStreamex.rangeClosed(0, 5).max().orElseThrow());
        assertEquals(3, IntStreamex.rangeClosed(0, 5).skip(3).limit(1).findFirst().orElseThrow());
    }

    @Test
    public void of() {
        assertEquals(1, IntStreamex.of(4).count());
        assertEquals(4, IntStreamex.of(4).findFirst().orElseThrow());
    }

    @Test
    public void iterate() {
        int[] expected = {0, 1, 2, 3, 4};
        int[] actual = IntStreamex.iterate(0, i -> i + 1).limit(5).toArray();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void collect() {
        IntStreamex intStream = IntStreamex.range(1, 10);
        AtomicInteger atomicInteger = intStream.filter(i -> i % 2 == 0)
                .parallel()
                .collect(AtomicInteger::new,
                        (a, b) -> a.set(a.get() + b),
                        (a, b) -> a.set(a.get() + b.get())
                );
        assertEquals(20, atomicInteger.get());
    }

    @Test
    public void flatMap() {
        List<Integer> source = Streamex.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toList());
        int[] destination = IntStreamex.range(1, 10)
                .flatMap(list -> {
                    int[] ints = new int[10];
                    for (int i = 0; i < ints.length; i++) {
                        ints[i] = source.get(i);
                    }
                    return IntStreamex.of(ints);
                })
                .toArray();
        Integer expected = source.get(3);
        Integer actual = destination[3];
        assertEquals(expected, actual);
    }

    IntStreamex createStream(int... values) {
        return IntStreamex.of(values);
    }

    IntStreamex createStream() {
        return createStream(1, 3, 2, 4, 5, 0);
    }

}