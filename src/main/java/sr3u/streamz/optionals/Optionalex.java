package sr3u.streamz.optionals;

import sr3u.streamz.functionals.Consumerex;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;

import java.util.Optional;
import java.util.function.Supplier;

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
        internal.ifPresent(consumer.wrap());
    }

    public Optionalex<T> filter(Predicatex<? super T> predicate) {
        return Optionalex.ofOptional(internal.filter(predicate.wrap()));
    }

    public <U> Optionalex<U> map(Functionex<? super T, ? extends U> mapper) {
        return Optionalex.ofOptional(internal.map(mapper.wrap()));
    }

    public OptionalIntex mapToInt(ToIntFunctionex<T> mapper) {
        if (isPresent()) {
            int i = mapper.wrap().applyAsInt(get());
            return OptionalIntex.of(i);
        } else {
            return OptionalIntex.empty();
        }
    }

    public OptionalLongex mapToLong(ToLongFunctionex<T> mapper) {
        if (isPresent()) {
            long i = mapper.wrap().applyAsLong(get());
            return OptionalLongex.of(i);
        } else {
            return OptionalLongex.empty();
        }
    }

    public OptionalDoublex mapToDouble(ToDoubleFunctionex<T> mapper) {
        if (isPresent()) {
            double i = mapper.wrap().applyAsDouble(get());
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

    public T orElseGet(Supplierex<? extends T> other) {
        return internal.orElseGet(other.wrap());
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return internal.orElseThrow(exceptionSupplier);
    }

    public T orElseThrow() {
        return orElseThrow(RuntimeException::new);
    }
}
