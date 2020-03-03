package sr3u.streamex.functionals.primitive.longinteger;


import sr3u.streamex.functionals.Supplierex;

/**
 * Represents a supplier of {@code long}-valued results.  This is the
 * {@code long}-producing primitive specialization of {@link Supplierex}.
 *
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #getAsLong()}.
 *
 * @see Supplierex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface LongSupplierex {

    /**
     * Gets a result.
     *
     * @return a result
     */
    @SuppressWarnings("RedundantThrows")
    long getAsLong() throws Exception;
}
