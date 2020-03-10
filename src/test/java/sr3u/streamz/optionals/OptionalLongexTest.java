package sr3u.streamz.optionals;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class OptionalLongexTest {
    public static final double DELTA = 1e-10;

    @Test
    public void empty() {
        assertFalse(OptionalLongex.empty().isPresent());
    }

    @Test
    public void boxed() {
        assertEquals(10, OptionalLongex.of(10).boxed().orElseThrow().intValue());
        assertThrows(Exception.class, () -> OptionalLongex.empty().boxed().get());
    }

    @Test
    public void getAsLong() {
        assertEquals(10, OptionalLongex.of(10).getAsLong());
        assertThrows(Exception.class, () -> OptionalLongex.empty().getAsLong());
    }

    @Test
    public void asDouble() {
        assertEquals(10.0, OptionalLongex.of(10).asDouble().orElseThrow(), DELTA);
        assertThrows(Exception.class, () -> OptionalLongex.empty().asDouble().getAsDouble());
    }

    @Test
    public void isPresent() {
        assertFalse(OptionalLongex.empty().isPresent());
        assertTrue(OptionalLongex.of(1).isPresent());
    }

    @Test
    public void ifPresent() {
        AtomicBoolean passed = new AtomicBoolean(false);
        OptionalLongex.of(10).ifPresent(i -> passed.set(true));
        assertTrue(passed.get());
        AtomicBoolean failed = new AtomicBoolean(true);
        OptionalLongex.empty().ifPresent(i -> passed.set(false));
        assertTrue(failed.get());
    }

    @Test
    public void orElse() {
        assertEquals(10, OptionalLongex.of(10).orElse(12));
        assertEquals(12, OptionalLongex.empty().orElse(12));
    }

    @Test
    public void orElseGet() {
        assertEquals(10, OptionalLongex.of(10).orElseGet(() -> 12));
        assertEquals(12, OptionalLongex.empty().orElseGet(() -> 12));
    }

    @Test
    public void orElseThrow() throws Exception {
        assertThrows(Exception.class, () -> OptionalLongex.empty().orElseThrow(Exception::new));
        System.out.println(OptionalLongex.of(10).orElseThrow(Exception::new));
    }
}