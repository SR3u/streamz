package sr3u.streamz.functionals.primitive.doublefloat;


import sr3u.streamz.functionals.Supplierex;

/**
 * Represents a supplier of {@code double}-valued results.  This is the
 * {@code double}-producing primitive specialization of {@link Supplierex}.
 *
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #getAsDouble()}.
 *
 * @see Supplierex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface DoubleSupplierex {

    /**
     * Gets a result.
     *
     * @return a result
     */
    @SuppressWarnings("RedundantThrows")
    double getAsDouble() throws Exception;
}
