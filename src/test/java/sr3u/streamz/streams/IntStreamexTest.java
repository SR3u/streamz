package sr3u.streamz.streams;

import org.junit.Test;

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
        assertEquals(0, createStream().sorted().findFirst().orElseThrow(RuntimeException::new));
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
                .orElseThrow(RuntimeException::new));
    }

    @Test
    public void minMax() {
        assertEquals(0, createStream().min().orElseThrow(RuntimeException::new));
        assertEquals(5, createStream().max().orElseThrow(RuntimeException::new));
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
                .mapToInt(i -> (int) i)
                .asLongStream()
                .mapToInt(i -> (int) i)
                .skip(3)
                .limit(1)
                .findFirst().orElseThrow(RuntimeException::new));
    }

    @Test
    public void sum() {
        assertEquals(15, createStream().sum());
    }

    @Test
    public void average() {
        double average = createStream().average().orElseThrow(RuntimeException::new);
        assertEquals(2.5, average, 1e-10);
    }

    @Test
    public void parallelAndSequential() {
        assertTrue(createStream().parallel().isParallel());
        assertFalse(createStream().sequential().isParallel());
    }

    IntStreamex createStream(int... values) {
        return IntStreamex.of(values);
    }

    IntStreamex createStream() {
        return createStream(1, 3, 2, 4, 5, 0);
    }

}