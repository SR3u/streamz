package sr3u.streamz.functionals;

import sr3u.streamz.common.ExceptionWrapper;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumerex} is expected
 * to operate via side-effects.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #accept(Object)}.
 *
 * @param <T> the type of the input to the operation
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface Consumerex<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws Exception;

    /**
     * Returns a composed {@code Consumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code Consumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumerex<T> andThen(Consumerex<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    default Consumer<T> wrap() {
        return ExceptionWrapper.wrap(this);
    }
}
