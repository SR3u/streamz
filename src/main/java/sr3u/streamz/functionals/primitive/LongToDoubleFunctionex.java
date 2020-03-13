package sr3u.streamz.functionals.primitive;

import sr3u.streamz.common.primitive.LongExceptionWrapper;
import sr3u.streamz.functionals.Functionex;

import java.util.function.LongToDoubleFunction;

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

    default LongToDoubleFunction wrap() {
        return LongExceptionWrapper.wrap(this);
    }
}

