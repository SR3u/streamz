package sr3u.streamz.futures.impl;

import sr3u.streamz.futures.Futurex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * @since v1.8.0.3-a
 */
public class FuturexSupport {

    public static final ExecutorService DEFAULT_EXECUTOR = Executors.newSingleThreadExecutor();

    public static <T> Futurex<T> createWithFuture(Future<T> future) {
        return createWithFuture(future, DEFAULT_EXECUTOR);
    }

    public static <T> Futurex<T> createWithFuture(Future<T> future, ExecutorService executorService) {
        return createWithFuture(future, () -> executorService);
    }

    public static <T> Futurex<T> createWithFuture(Future<T> future, Supplier<ExecutorService> executorServiceSupplier) {
        return new WrappedFuture<>(future, executorServiceSupplier);
    }

}
