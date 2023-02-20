package sr3u.streamz.futures;

import sr3u.streamz.common.ExceptionWrapper;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.futures.impl.FuturexSupport;
import sr3u.streamz.optionals.Optionalex;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @since v1.8.0.3-a
 */
public interface Futurex<V> {

    static <T> Futurex<T> withFuture(Future<T> future) {
        return FuturexSupport.createWithFuture(future);
    }

    Future<V> toFuture();

    boolean cancel(boolean mayInterruptIfRunning);

    boolean isCancelled();

    boolean isDone();

    V get() throws InterruptedException, ExecutionException;

    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    ExecutorService defaultExecutor();

    default <T> Futurex<T> map(Functionex<V, T> mapping) {
        return map(mapping, defaultExecutor());
    }

    <T> Futurex<T> map(Functionex<V, T> mapping, ExecutorService executorService);

    default <T> Futurex<T> mapWithTimeout(Functionex<V, T> mapping, long timeout, TimeUnit unit) {
        return mapWithTimeout(mapping, defaultExecutor(), timeout, unit);
    }

    <T> Futurex<T> mapWithTimeout(Functionex<V, T> mapping, ExecutorService executorService, long timeout, TimeUnit unit);

    default Optionalex<V> getOptional() throws InterruptedException, ExecutionException {
        return Optionalex.ofNullable(get());
    }

    default Optionalex<V> getOptional(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return Optionalex.ofNullable(get(timeout, unit));
    }

    default Optionalex<V> getOptionalSilent() {
        return Optionalex.ofNullable(getSilent());
    }

    default Optionalex<V> getOptionalSilent(long timeout, TimeUnit unit) {
        return Optionalex.ofNullable(getSilent(timeout, unit));
    }

    default V getSilent() {
        return ExceptionWrapper.wrap(() -> get()).get();
    }

    default V getSilent(long timeout, TimeUnit unit) {
        return ExceptionWrapper.wrap(() -> get(timeout, unit)).get();
    }

}
