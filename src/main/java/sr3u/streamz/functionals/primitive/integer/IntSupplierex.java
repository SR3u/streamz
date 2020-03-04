package sr3u.streamz.functionals.primitive.integer;


import sr3u.streamz.functionals.Supplierex;

/**
 * Represents a supplier of {@code int}-valued results.  This is the
 * {@code int}-producing primitive specialization of {@link Supplierex}.
 *
 * <p>There is no requirement that a distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #getAsInt()}.
 *
 * @see Supplierex
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface IntSupplierex {

    /**
     * Gets a result.
     *
     * @return a result
     */
    @SuppressWarnings("RedundantThrows")
    int getAsInt() throws Exception;
}
