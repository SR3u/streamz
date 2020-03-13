package sr3u.streamz.functionals.primitive.doublefloat;

import sr3u.streamz.common.primitive.DoubleExceptionWrapper;
import sr3u.streamz.functionals.UnaryOperatorex;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents an operation on a single {@code double}-valued operand that produces
 * an {@code double}-valued result.  This is the primitive type specialization of
 * {@link UnaryOperatorex} for {@code double}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsDouble(double)}.
 *
 * @see UnaryOperatorex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface DoubleUnaryOperatorex {

    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    double applyAsDouble(double operand) throws Exception;

    /**
     * Returns a composed operator that first applies the {@code before}
     * operator to its input, and then applies this operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before}
     * operator and then applies this operator
     * @throws NullPointerException if before is null
     * @see #andThen(DoubleUnaryOperatorex)
     */
    default DoubleUnaryOperatorex compose(DoubleUnaryOperatorex before) {
        Objects.requireNonNull(before);
        return (double v) -> applyAsDouble(before.applyAsDouble(v));
    }

    /**
     * Returns a composed operator that first applies this operator to
     * its input, and then applies the {@code after} operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after the operator to apply after this operator is applied
     * @return a composed operator that first applies this operator and then
     * applies the {@code after} operator
     * @throws NullPointerException if after is null
     * @see #compose(DoubleUnaryOperatorex)
     */
    default DoubleUnaryOperatorex andThen(DoubleUnaryOperatorex after) {
        Objects.requireNonNull(after);
        return (double t) -> after.applyAsDouble(applyAsDouble(t));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static DoubleUnaryOperatorex identity() {
        return t -> t;
    }

    default DoubleUnaryOperator wrap() {
        return DoubleExceptionWrapper.wrap(this);
    }
}
