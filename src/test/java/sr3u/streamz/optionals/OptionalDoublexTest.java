package sr3u.streamz.optionals;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class OptionalDoublexTest {
    public static final double DELTA = 1e-10;

    @Test
    public void empty() {
        assertFalse(OptionalDoublex.empty().isPresent());
    }

    @Test
    public void boxed() {
        assertEquals(10, OptionalDoublex.of(10).boxed().orElseThrow().intValue());
        assertThrows(Exception.class, () -> OptionalDoublex.empty().boxed().get());
    }

    @Test
    public void getAsDouble() {
        assertEquals(10, OptionalDoublex.of(10).getAsDouble(), DELTA);
        assertThrows(Exception.class, () -> OptionalDoublex.empty().getAsDouble());
    }

    @Test
    public void isPresent() {
        assertFalse(OptionalDoublex.empty().isPresent());
        assertTrue(OptionalDoublex.of(1).isPresent());
    }

    @Test
    public void ifPresent() {
        AtomicBoolean passed = new AtomicBoolean(false);
        OptionalDoublex.of(10).ifPresent(i -> passed.set(true));
        assertTrue(passed.get());
        AtomicBoolean failed = new AtomicBoolean(true);
        OptionalDoublex.empty().ifPresent(i -> passed.set(false));
        assertTrue(failed.get());
    }

    @Test
    public void orElse() {
        assertEquals(10, OptionalDoublex.of(10).orElse(12), DELTA);
        assertEquals(12, OptionalDoublex.empty().orElse(12), DELTA);
    }

    @Test
    public void orElseGet() {
        assertEquals(10, OptionalDoublex.of(10).orElseGet(() -> 12), DELTA);
        assertEquals(12, OptionalDoublex.empty().orElseGet(() -> 12), DELTA);
    }

    @Test
    public void orElseThrow() throws Exception {
        assertThrows(Exception.class, () -> OptionalDoublex.empty().orElseThrow(Exception::new));
        System.out.println(OptionalDoublex.of(10).orElseThrow(Exception::new));
    }
}