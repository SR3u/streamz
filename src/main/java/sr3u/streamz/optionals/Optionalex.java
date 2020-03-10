package sr3u.streamz.optionals;

import sr3u.streamz.functionals.Consumerex;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;

import java.util.Optional;
import java.util.function.Supplier;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.ExceptionWrapper.wrapOptional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Optionalex<T> {
    private final Optional<T> internal;

    public Optional<T> optional() {
        return internal;
    }

    public Optionalex(Optional<T> optional) {
        this.internal = optional;
    }

    public static <T> Optionalex<T> ofOptional(Optional<T> optional) {
        return new Optionalex<>(optional);
    }

    public static <T1> Optionalex<T1> empty() {
        return ofOptional(Optional.empty());
    }

    public static <T1> Optionalex<T1> of(T1 value) {
        return ofOptional(Optional.of(value));
    }

    public static <T1> Optionalex<T1> ofNullable(T1 value) {
        return ofOptional(Optional.ofNullable(value));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public T get() {
        return internal.get();
    }

    public boolean isPresent() {
        return internal.isPresent();
    }

    public void ifPresent(Consumerex<? super T> consumer) {
        internal.ifPresent(wrap(consumer));
    }

    public Optionalex<T> filter(Predicatex<? super T> predicate) {
        return Optionalex.ofOptional(internal.filter(wrap(predicate)));
    }

    public <U> Optionalex<U> map(Functionex<? super T, ? extends U> mapper) {
        return Optionalex.ofOptional(internal.map(wrap(mapper)));
    }

    public OptionalIntex mapToInt(ToIntFunctionex<T> mapper) {
        if (isPresent()) {
            int i = wrap(mapper).applyAsInt(get());
            return OptionalIntex.of(i);
        } else {
            return OptionalIntex.empty();
        }
    }

    public OptionalLongex mapToLong(ToLongFunctionex<T> mapper) {
        if (isPresent()) {
            long i = wrap(mapper).applyAsLong(get());
            return OptionalLongex.of(i);
        } else {
            return OptionalLongex.empty();
        }
    }

    public OptionalDoublex mapToDouble(ToDoubleFunctionex<T> mapper) {
        if (isPresent()) {
            double i = wrap(mapper).applyAsDouble(get());
            return OptionalDoublex.of(i);
        } else {
            return OptionalDoublex.empty();
        }
    }

    public <U> Optionalex<U> flatMap(Functionex<? super T, Optionalex<U>> mapper) {
        return Optionalex.ofOptional(internal.flatMap(wrapOptional(mapper)));
    }

    public T orElse(T other) {
        return internal.orElse(other);
    }

    public T orElseGet(Supplier<? extends T> other) {
        return internal.orElseGet(other);
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return internal.orElseThrow(exceptionSupplier);
    }

    public T orElseThrow() {
        return orElseThrow(RuntimeException::new);
    }
}
