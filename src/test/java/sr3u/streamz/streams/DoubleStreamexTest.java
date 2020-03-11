package sr3u.streamz.streams;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoubleStreamexTest {

    public static final double DELTA = 1e-10;

    @Test
    public void empty() {
        assertEquals(0, DoubleStreamex.empty().count());
    }

    @Test
    public void concatAndDistinct() {
        assertEquals(12, DoubleStreamex.concat(createStream(), createStream())
                .count());
        assertEquals(6, DoubleStreamex.concat(createStream(), createStream())
                .distinct()
                .count());
    }

    @Test
    public void sortedAndFindFirst() {
        assertEquals(0, createStream().sorted().findFirst().orElseThrow(), DELTA);
    }

    @Test
    public void skipAndLimit() {
        double[] doubles = createStream()
                .skip(2)
                .limit(1)
                .toArray();
        assertEquals(1, doubles.length);
        assertEquals(2, doubles[0], 1e-10);
    }

    @Test
    public void skipAndAndFindAny() {
        assertEquals(2, createStream()
                .skip(2)
                .limit(1)
                .findAny()
                .orElseThrow(), DELTA);
    }

    @Test
    public void filter() {
        assertEquals(3, createStream()
                .filter(i -> i == 3)
                .findFirst()
                .orElseThrow(), DELTA);
    }

    @Test
    public void minMax() {
        assertEquals(0, createStream().min().orElseThrow(), DELTA);
        assertEquals(5, createStream().max().orElseThrow(), DELTA);
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
    public void sum() {
        assertEquals(15, createStream().sum(), DELTA);
    }

    @Test
    public void average() {
        double average = createStream().average().orElseThrow();
        assertEquals(2.5, average, DELTA);
    }

    @Test
    public void of() {
        assertEquals(1, DoubleStreamex.of(4).count());
        assertEquals(4, DoubleStreamex.of(4).findFirst().orElseThrow(), DELTA);
    }

    @Test
    public void parallelAndSequential() {
        assertTrue(createStream().parallel().isParallel());
        assertFalse(createStream().sequential().isParallel());
    }

    @Test
    public void iterate() {
        double[] expected = {0.4, 1.4, 2.4, 3.4, 4.4};
        double[] actual = DoubleStreamex.iterate(0.4, i -> i + 1).limit(5).toArray();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i], DELTA);
        }
    }

    @Test
    public void generate() {
        double[] expected = {0, 1, 2, 3, 4};
        AtomicInteger ai = new AtomicInteger();
        double[] actual = DoubleStreamex.generate(() -> expected[ai.getAndIncrement()]).limit(5).toArray();
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i], DELTA);
        }
    }

    @Test
    public void collect() {
        LongStreamex longStream = LongStreamex.range(1, 10);
        double result = longStream.filter(i -> i % 2 == 0)
                .mapToDouble(i -> (double) i)
                .map(i -> i + 0.4)
                .collect(DoubleWrapper::new,
                        (a, b) -> a.set(a.get() + b),
                        (a, b) -> a.set(a.get() + b.get())
                ).get();
        assertEquals(21.6, result, DELTA);
    }

    @Test
    public void reduce() {
        LongStreamex longStream = LongStreamex.range(1, 10);
        double result = longStream.filter(i -> i % 2 == 0)
                .mapToDouble(i -> (double) i)
                .map(i -> i + 0.4)
                .reduce(0, Double::sum);
        assertEquals(21.6, result, DELTA);
        longStream = LongStreamex.range(1, 10);
        result = longStream.filter(i -> i % 2 == 0)
                .mapToDouble(i -> (double) i)
                .map(i -> i + 0.4)
                .reduce(Double::sum).orElseThrow();
        assertEquals(21.6, result, DELTA);
    }

    @Test
    public void flatMap() {
        List<Double> source = Streamex.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map(Double::new).collect(Collectors.toList());
        double[] destination = DoubleStreamex.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .flatMap(list -> {
                    double[] doubles = new double[10];
                    for (int i = 0; i < doubles.length; i++) {
                        doubles[i] = source.get(i);
                    }
                    return DoubleStreamex.of(doubles);
                })
                .toArray();
        double expected = source.get(3);
        double actual = destination[3];
        assertEquals(expected, actual, DELTA);
    }

    DoubleStreamex createStream(double... values) {
        return DoubleStreamex.of(values);
    }

    DoubleStreamex createStream() {
        return createStream(1, 3, 2, 4, 5, 0);
    }

    private static class DoubleWrapper {
        private double value = 0.0;

        public double get() {
            return value;
        }

        public void set(double value) {
            this.value = value;
        }
    }

}