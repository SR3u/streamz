package sr3u.streamz.functionals.primitive;

import sr3u.streamz.functionals.Functionex;

/**
 * Represents a function that accepts an int-valued argument and produces a
 * double-valued result.  This is the {@code int}-to-{@code double} primitive
 * specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsDouble(int)}.
 *
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface IntToDoubleFunctionex {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    double applyAsDouble(int value) throws Exception;
}
