package sr3u.streamz.functionals;

/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a {@link FunctionalInterface}
 * whose functional method is {@link #get()}.
 *
 * @param <T> the type of results supplied by this supplier
 * @since 1.8.0.1
 */
@FunctionalInterface
public interface Supplierex<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    @SuppressWarnings("RedundantThrows")
    T get() throws Exception;
}