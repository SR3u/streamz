package sr3u.streamz.functionals.primitive.integer;

import sr3u.streamz.functionals.Functionex;

/**
 * Represents a function that produces an int-valued result.  This is the
 * {@code int}-producing primitive specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsInt(Object)}.
 *
 * @param <T> the type of the input to the function
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface ToIntFunctionex<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    int applyAsInt(T value) throws Exception;

}
