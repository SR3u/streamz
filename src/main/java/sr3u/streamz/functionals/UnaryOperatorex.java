package sr3u.streamz.functionals;

/**
 * Represents an operation on a single operand that produces a result of the
 * same type as its operand.  This is a specialization of {@code Function} for
 * the case where the operand and result are of the same type.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the operand and result of the operator
 * @see Functionex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface UnaryOperatorex<T> extends Functionex<T, T> {

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @param <T> the type of the input and output of the operator
     * @return a unary operator that always returns its input argument
     */
    static <T> UnaryOperatorex<T> identity() {
        return t -> t;
    }
}
