package sr3u.streamz.functionals.primitive;


import sr3u.streamz.functionals.Functionex;

/**
 * Represents a function that accepts a double-valued argument and produces a
 * long-valued result.  This is the {@code double}-to-{@code long} primitive
 * specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsLong(double)}.
 *
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface DoubleToLongFunctionex {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    long applyAsLong(double value) throws Exception;
}
