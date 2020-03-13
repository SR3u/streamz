package sr3u.streamz.functionals.primitive.integer;

import sr3u.streamz.common.primitive.IntExceptionWrapper;
import sr3u.streamz.functionals.BiConsumerex;

import java.util.function.ObjIntConsumer;

/**
 * Represents an operation that accepts an object-valued and a
 * {@code int}-valued argument, and returns no result.  This is the
 * {@code (reference, int)} specialization of {@link BiConsumerex}.
 * Unlike most other functional interfaces, {@code ObjIntConsumer} is
 * expected to operate via side-effects.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #accept(Object, int)}.
 *
 * @param <T> the type of the object argument to the operation
 * @see BiConsumerex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface ObjIntConsumerex<T> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t     the first input argument
     * @param value the second input argument
     */
    @SuppressWarnings("RedundantThrows")
    void accept(T t, int value) throws Exception;

    default ObjIntConsumer<T> wrap() {
        return IntExceptionWrapper.wrap(this);
    }
}
