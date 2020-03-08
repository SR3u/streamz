package sr3u.streamz.optionals;

import org.junit.Test;
import sr3u.streamz.test.Item;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class OptionalexTest {

    public static final double DELTA = 1e-10;

    @Test
    public void empty() {
        assertFalse(Optionalex.empty().isPresent());
    }

    @Test
    public void of() {
    }

    @Test
    public void get() {
        assertEquals(1, createOptional().get().getAnInt());
        assertThrows(Exception.class, () -> Optionalex.empty().get());
    }

    @Test
    public void isPresent() {
        assertTrue(createOptional().isPresent());
    }

    @Test
    public void ifPresent() {
        AtomicBoolean passed = new AtomicBoolean(false);
        createOptional().ifPresent(i -> passed.set(true));
        assertTrue(passed.get());
        AtomicBoolean failed = new AtomicBoolean(true);
        Optionalex.empty().ifPresent(i -> passed.set(false));
        assertTrue(failed.get());
    }

    @Test
    public void filter() {
        assertTrue(createOptional().filter(i -> i.getAnInt() == 1).isPresent());
        assertFalse(createOptional().filter(i -> i.getAnInt() == 100).isPresent());
    }

    @Test
    public void map() {
        Item item = createOptional().map(i -> i.setAnInt(100)).orElseThrow(RuntimeException::new);
        assertEquals(100, item.getAnInt());
    }

    @Test
    public void mapToInt() {
        assertEquals(1, createOptional().mapToInt(Item::getAnInt).orElseThrow(RuntimeException::new));
    }

    @Test
    public void mapToLong() {
        assertEquals(1L, createOptional().mapToLong(Item::getaLong).orElseThrow(RuntimeException::new));
    }

    @Test
    public void mapToDouble() {
        assertEquals(1.4, createOptional().mapToDouble(Item::getaDouble).orElseThrow(RuntimeException::new), DELTA);
    }

    @Test
    public void flatMap() {
        Item item = createOptional().flatMap(Optionalex::ofNullable).orElseThrow(RuntimeException::new);
        assertEquals(1, item.getAnInt());
        assertFalse(createOptional().flatMap(i -> Optionalex.empty()).isPresent());
    }

    @Test
    public void orElse() {
        assertEquals(1, createOptional().orElse(new Item("0", 0, 0, 0)).getAnInt());
        Item item = (Item) Optionalex.empty().orElse(new Item("0", 0, 0, 0));
        assertEquals(0, item.getAnInt());
    }

    @Test
    public void orElseGet() {
        assertEquals(1, createOptional().orElseGet(() -> new Item("0", 0, 0, 0)).getAnInt());
        Item item = (Item) Optionalex.empty().orElseGet(() -> new Item("0", 0, 0, 0));
        assertEquals(0, item.getAnInt());
    }

    @Test
    public void orElseThrow() {
        assertThrows(Exception.class, () -> Optionalex.empty().orElseThrow(Exception::new));
    }

    public Optionalex<Item> createOptional() {
        return Optionalex.ofNullable(new Item("" + 1, 1, 1, 1 + 0.4));
    }
}