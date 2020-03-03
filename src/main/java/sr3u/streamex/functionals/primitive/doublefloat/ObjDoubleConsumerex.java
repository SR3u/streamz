package sr3u.streamex.functionals.primitive.doublefloat;

import sr3u.streamex.functionals.BiConsumerex;

/**
 * Represents an operation that accepts an object-valued and a
 * {@code double}-valued argument, and returns no result.  This is the
 * {@code (reference, double)} specialization of {@link BiConsumerex}.
 * Unlike most other functional interfaces, {@code ObjDoubleConsumer} is
 * expected to operate via side-effects.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #accept(Object, double)}.
 *
 * @param <T> the type of the object argument to the operation
 * @see BiConsumerex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface ObjDoubleConsumerex<T> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t     the first input argument
     * @param value the second input argument
     */
    @SuppressWarnings("RedundantThrows")
    void accept(T t, double value) throws Exception;
}
