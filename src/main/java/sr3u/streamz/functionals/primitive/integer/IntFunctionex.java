package sr3u.streamz.functionals.primitive.integer;

import sr3u.streamz.common.primitive.IntExceptionWrapper;
import sr3u.streamz.functionals.Functionex;

import java.util.function.IntFunction;

/**
 * Represents a function that accepts an int-valued argument and produces a
 * result.  This is the {@code int}-consuming primitive specialization for
 * {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #apply(int)}.
 *
 * @param <R> the type of the result of the function
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface IntFunctionex<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    R apply(int value) throws Exception;

    default IntFunction<R> wrap() {
        return IntExceptionWrapper.wrap(this);
    }
}
