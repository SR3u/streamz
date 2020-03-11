package sr3u.streamz.streams;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LongStreamexTest {

    @Test
    public void empty() {
        assertEquals(0, LongStreamex.empty().count());
    }

    @Test
    public void concatAndDistinct() {
        assertEquals(12, LongStreamex.concat(createStream(), createStream())
                .count());
        assertEquals(6, LongStreamex.concat(createStream(), createStream())
                .distinct()
                .count());
    }

    @Test
    public void sortedAndFindFirst() {
        assertEquals(0, createStream().sorted().findFirst().orElseThrow());
    }

    @Test
    public void skipAndLimit() {
        long[] longs = createStream()
                .skip(2)
                .limit(1)
                .toArray();
        assertEquals(1, longs.length);
        assertEquals(2, longs[0]);
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
                .asDoubleStream()
                .mapToLong(i -> (long) i)
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
    public void range() {
        assertEquals(0, LongStreamex.range(0, 5).min().orElseThrow());
        assertEquals(4, LongStreamex.range(0, 5).max().orElseThrow());
        assertEquals(3, LongStreamex.range(0, 5).skip(3).limit(1).findFirst().orElseThrow());
    }

    @Test
    public void rangeClosed() {
        assertEquals(0, LongStreamex.rangeClosed(0, 5).min().orElseThrow());
        assertEquals(5, LongStreamex.rangeClosed(0, 5).max().orElseThrow());
        assertEquals(3, LongStreamex.rangeClosed(0, 5).skip(3).limit(1).findFirst().orElseThrow());
    }

    @Test
    public void of() {
        assertEquals(1, LongStreamex.of(4).count());
        assertEquals(4, LongStreamex.of(4).findFirst().orElseThrow());
    }

    @Test
    public void parallelAndSequential() {
        assertTrue(createStream().parallel().isParallel());
        assertFalse(createStream().sequential().isParallel());
    }

    @Test
    public void iterate() {
        long[] expected = {0, 1, 2, 3, 4};
        long[] actual = LongStreamex.iterate(0, i -> i + 1).limit(5).toArray();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void generate() {
        long[] expected = {0, 1, 2, 3, 4};
        AtomicInteger ai = new AtomicInteger();
        long[] actual = LongStreamex.generate(() -> expected[ai.getAndIncrement()]).limit(5).toArray();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void collect() {
        LongStreamex longStream = LongStreamex.range(1, 10);
        AtomicLong atomicInteger = longStream.filter(i -> i % 2 == 0)
                .parallel()
                .collect(AtomicLong::new,
                        (a, b) -> a.set(a.get() + b),
                        (a, b) -> a.set(a.get() + b.get())
                );
        assertEquals(20, atomicInteger.get());
    }

    @Test
    public void reduce() {
        LongStreamex longStream = LongStreamex.range(1, 10);
        long result = longStream.filter(i -> i % 2 == 0)
                .reduce(0, Long::sum);
        assertEquals(20, result);
        longStream = LongStreamex.range(1, 10);
        result = longStream.filter(i -> i % 2 == 0)
                .reduce(Long::sum).orElseThrow();
        assertEquals(20, result);
    }

    @Test
    public void flatMap() {
        List<Long> source = Streamex.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map(Long::new).collect(Collectors.toList());
        long[] destination = LongStreamex.range(1, 10)
                .flatMap(list -> {
                    long[] logs = new long[10];
                    for (int i = 0; i < logs.length; i++) {
                        logs[i] = source.get(i);
                    }
                    return LongStreamex.of(logs);
                })
                .toArray();
        Long expected = source.get(3);
        Long actual = destination[3];
        assertEquals(expected, actual);
    }

    LongStreamex createStream(long... values) {
        return LongStreamex.of(values);
    }

    LongStreamex createStream() {
        return createStream(1, 3, 2, 4, 5, 0);
    }

}