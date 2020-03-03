package sr3u.streamex.functionals.primitive;

import sr3u.streamex.functionals.Functionex;

/**
 * Represents a function that accepts a long-valued argument and produces a
 * double-valued result.  This is the {@code long}-to-{@code double} primitive
 * specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsDouble(long)}.
 *
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface LongToDoubleFunctionex {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    double applyAsDouble(long value) throws Exception;
}

