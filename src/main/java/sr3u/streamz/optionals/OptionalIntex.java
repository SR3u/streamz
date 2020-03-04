package sr3u.streamz.optionals;

import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.integer.IntConsumerex;
import sr3u.streamz.functionals.primitive.integer.IntSupplierex;

import java.util.OptionalInt;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.IntExceptionWrapper.wrap;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OptionalIntex {
    private final OptionalInt internal;

    private OptionalIntex(OptionalInt optionalInt) {
        internal = optionalInt;
    }

    public static OptionalIntex empty() {
        return ofOptional(OptionalInt.empty());
    }

    public static OptionalIntex of(int value) {
        return ofOptional(OptionalInt.of(value));
    }

    public static OptionalIntex ofOptional(OptionalInt optionalInt) {
        return new OptionalIntex(optionalInt);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public int getAsInt() {
        return internal.getAsInt();
    }

    public boolean isPresent() {
        return internal.isPresent();
    }

    public void ifPresent(IntConsumerex consumer) {
        internal.ifPresent(wrap(consumer));
    }

    public int orElse(int other) {
        return internal.orElse(other);
    }

    public int orElseGet(IntSupplierex other) {
        return internal.orElseGet(wrap(other));
    }

    public <X extends Throwable> int orElseThrow(Supplierex<X> exceptionSupplier) throws X {
        return internal.orElseThrow(wrap(exceptionSupplier));
    }
}
