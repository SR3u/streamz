package sr3u.streamex.functionals.primitive.integer;

import sr3u.streamex.functionals.BinaryOperatorex;

/**
 * Represents an operation upon two {@code int}-valued operands and producing an
 * {@code int}-valued result.   This is the primitive type specialization of
 * {@link BinaryOperatorex} for {@code int}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsInt(int, int)}.
 *
 * @see BinaryOperatorex
 * @see IntUnaryOperatorex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface IntBinaryOperatorex {

    /**
     * Applies this operator to the given operands.
     *
     * @param left  the first operand
     * @param right the second operand
     * @return the operator result
     */
    @SuppressWarnings("RedundantThrows")
    int applyAsInt(int left, int right) throws Exception;
}
