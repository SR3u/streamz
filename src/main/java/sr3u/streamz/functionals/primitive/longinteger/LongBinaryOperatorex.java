package sr3u.streamz.functionals.primitive.longinteger;

import sr3u.streamz.common.primitive.LongExceptionWrapper;
import sr3u.streamz.functionals.BinaryOperatorex;

import java.util.function.LongBinaryOperator;

/**
 * Represents an operation upon two {@code long}-valued operands and producing an
 * {@code long}-valued result.   This is the primitive type specialization of
 * {@link BinaryOperatorex} for {@code long}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #applyAsLong(long, long)}.
 *
 * @see BinaryOperatorex
 * @see LongUnaryOperatorex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface LongBinaryOperatorex {

    /**
     * Applies this operator to the given operands.
     *
     * @param left  the first operand
     * @param right the second operand
     * @return the operator result
     */
    @SuppressWarnings("RedundantThrows")
    long applyAsLong(long left, long right) throws Exception;

    default LongBinaryOperator wrap() {
        return LongExceptionWrapper.wrap(this);
    }
}
