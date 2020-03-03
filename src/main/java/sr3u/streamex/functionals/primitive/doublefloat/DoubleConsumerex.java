package sr3u.streamex.functionals.primitive.doublefloat;

import sr3u.streamex.functionals.Consumerex;

import java.util.Objects;

/**
 * Represents an operation that accepts a single {@code double}-valued argument and
 * returns no result.  This is the primitive type specialization of
 * {@link Consumerex} for {@code double}.  Unlike most other functional interfaces,
 * {@code DoubleConsumer} is expected to operate via side-effects.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #accept(double)}.
 *
 * @see Consumerex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface DoubleConsumerex {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(double value) throws Exception;

    /**
     * Returns a composed {@code DoubleConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code DoubleConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default DoubleConsumerex andThen(DoubleConsumerex after) {
        Objects.requireNonNull(after);
        return (double t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
