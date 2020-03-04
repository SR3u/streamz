package sr3u.streamz.functionals.primitive.longinteger;

import sr3u.streamz.functionals.BiConsumerex;

/**
 * Represents an operation that accepts an object-valued and a
 * {@code long}-valued argument, and returns no result.  This is the
 * {@code (reference, long)} specialization of {@link BiConsumerex}.
 * Unlike most other functional interfaces, {@code ObjLongConsumer} is
 * expected to operate via side-effects.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #accept(Object, long)}.
 *
 * @param <T> the type of the object argument to the operation
 * @see BiConsumerex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface ObjLongConsumerex<T> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t     the first input argument
     * @param value the second input argument
     */
    @SuppressWarnings("RedundantThrows")
    void accept(T t, long value) throws Exception;
}
