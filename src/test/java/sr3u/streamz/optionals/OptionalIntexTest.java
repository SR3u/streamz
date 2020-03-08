package sr3u.streamz.optionals;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class OptionalIntexTest {

    public static final double DELTA = 1e-10;

    @Test
    public void empty() {
        assertFalse(OptionalIntex.empty().isPresent());
    }

    @Test
    public void boxed() {
        assertEquals(10, OptionalIntex.of(10).boxed().orElseThrow(RuntimeException::new).intValue());
    }

    @Test
    public void getAsInt() {
        assertEquals(10, OptionalIntex.of(10).getAsInt());
        assertThrows(Exception.class, () -> OptionalIntex.empty().getAsInt());
    }

    @Test
    public void asLong() {
        assertEquals(10L, OptionalIntex.of(10).asLong().orElseThrow(RuntimeException::new));
    }

    @Test
    public void asDouble() {
        assertEquals(10.0, OptionalIntex.of(10).asDouble().orElseThrow(RuntimeException::new), DELTA);
    }

    @Test
    public void isPresent() {
        assertFalse(OptionalIntex.empty().isPresent());
        assertTrue(OptionalIntex.of(1).isPresent());
    }

    @Test
    public void ifPresent() {
        AtomicBoolean passed = new AtomicBoolean(false);
        OptionalIntex.of(10).ifPresent(i -> passed.set(true));
        assertTrue(passed.get());
        AtomicBoolean failed = new AtomicBoolean(true);
        OptionalIntex.empty().ifPresent(i -> passed.set(false));
        assertTrue(failed.get());
    }

    @Test
    public void orElse() {
        assertEquals(10, OptionalIntex.of(10).orElse(12));
        assertEquals(12, OptionalIntex.empty().orElse(12));
    }

    @Test
    public void orElseGet() {
    }

    @Test
    public void orElseThrow() throws Exception {
        assertThrows(Exception.class, () -> OptionalIntex.empty().orElseThrow(Exception::new));
        System.out.println(OptionalIntex.of(10).orElseThrow(Exception::new));
    }
}