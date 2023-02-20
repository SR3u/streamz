package sr3u.streamz.futures.impl;

import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.futures.Futurex;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * @since v1.8.0.3-a
 */
public class WrappedFuture<V> implements Futurex<V> {

    private final Future<V> future;
    private final Supplier<ExecutorService> executorSupplier;

    public WrappedFuture(Future<V> future, Supplier<ExecutorService> executorSupplier) {
        this.future = future;
        this.executorSupplier = executorSupplier;
    }

    @Override
    public Future<V> toFuture() {
        return future;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return future.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return future.isCancelled();
    }

    @Override
    public boolean isDone() {
        return future.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return future.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }

    @Override
    public ExecutorService defaultExecutor() {
        return executorSupplier.get();
    }

    @Override
    public <T> Futurex<T> map(Functionex<V, T> mapping, ExecutorService executorService) {
        return new WrappedFuture<>(executorService.submit(() -> mapping.apply(future.get())), () -> executorService);
    }

    @Override
    public <T> Futurex<T> mapWithTimeout(Functionex<V, T> mapping, ExecutorService executorService, long timeout, TimeUnit unit) {
        return new WrappedFuture<>(executorService.submit(() -> mapping.apply(future.get(timeout, unit))), () -> executorService);
    }
}
