package sr3u.streamz.functionals.primitive.doublefloat;

import sr3u.streamz.functionals.Functionex;

/**
 * Represents a function that produces a double-valued result.  This is the
 * {@code double}-producing primitive specialization for {@link Functionex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsDouble(Object)}.
 *
 * @param <T> the type of the input to the function
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface ToDoubleFunctionex<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @SuppressWarnings("RedundantThrows")
    double applyAsDouble(T value) throws Exception;

}
