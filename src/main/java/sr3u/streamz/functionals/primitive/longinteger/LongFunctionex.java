package sr3u.streamz.functionals.primitive.longinteger;

import sr3u.streamz.functionals.Functionex;

/**
 * Represents a function that accepts an long-valued argument and produces a
 * result.  This is the {@code long}-consuming primitive specialization for
 * {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #apply(long)}.
 *
 * @param <R> the type of the result of the function
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface LongFunctionex<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    R apply(long value) throws Exception;
}
