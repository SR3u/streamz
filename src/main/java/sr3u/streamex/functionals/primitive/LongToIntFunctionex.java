package sr3u.streamex.functionals.primitive;


import sr3u.streamex.functionals.Functionex;

/**
 * Represents a function that accepts a long-valued argument and produces an
 * int-valued result.  This is the {@code long}-to-{@code int} primitive
 * specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsInt(long)}.
 *
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface LongToIntFunctionex {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    int applyAsInt(long value) throws Exception;
}
