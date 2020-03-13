package sr3u.streamz.functionals.primitive;


import sr3u.streamz.common.primitive.LongExceptionWrapper;
import sr3u.streamz.functionals.Functionex;

import java.util.function.LongToIntFunction;

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

    default LongToIntFunction wrap() {
        return LongExceptionWrapper.wrap(this);
    }
}
