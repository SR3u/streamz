package sr3u.streamz.functionals.primitive.doublefloat;

import sr3u.streamz.common.primitive.DoubleExceptionWrapper;
import sr3u.streamz.functionals.Predicatex;

import java.util.Objects;
import java.util.function.DoublePredicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code double}-valued
 * argument. This is the {@code double}-consuming primitive type specialization of
 * {@link Predicatex}.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #test(double)}.
 *
 * @see Predicatex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface DoublePredicatex {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(double value) throws Exception;

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default DoublePredicatex and(DoublePredicatex other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default DoublePredicatex negate() {
        return (value) -> !test(value);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default DoublePredicatex or(DoublePredicatex other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }

    default DoublePredicate wrap() {
        return DoubleExceptionWrapper.wrap(this);
    }
}
