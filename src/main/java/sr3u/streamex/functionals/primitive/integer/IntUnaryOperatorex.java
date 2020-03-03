package sr3u.streamex.functionals.primitive.integer;

import sr3u.streamex.functionals.UnaryOperatorex;

import java.util.Objects;

/**
 * Represents an operation on a single {@code int}-valued operand that produces
 * an {@code int}-valued result.  This is the primitive type specialization of
 * {@link UnaryOperatorex} for {@code int}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsInt(int)}.
 *
 * @see UnaryOperatorex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface IntUnaryOperatorex {

    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    int applyAsInt(int operand) throws Exception;

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
     * @see #andThen(IntUnaryOperatorex)
     */
    default IntUnaryOperatorex compose(IntUnaryOperatorex before) {
        Objects.requireNonNull(before);
        return (int v) -> applyAsInt(before.applyAsInt(v));
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
     * @see #compose(IntUnaryOperatorex)
     */
    default IntUnaryOperatorex andThen(IntUnaryOperatorex after) {
        Objects.requireNonNull(after);
        return (int t) -> after.applyAsInt(applyAsInt(t));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static IntUnaryOperatorex identity() {
        return t -> t;
    }
}
