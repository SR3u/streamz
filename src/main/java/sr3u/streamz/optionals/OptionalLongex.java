package sr3u.streamz.optionals;

import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.longinteger.LongConsumerex;
import sr3u.streamz.functionals.primitive.longinteger.LongSupplierex;

import java.util.OptionalLong;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.LongExceptionWrapper.wrap;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OptionalLongex {

    private final OptionalLong internal;

    public OptionalLongex(OptionalLong optionalLong) {
        this.internal = optionalLong;
    }

    public static OptionalLongex ofOptional(OptionalLong optionalLong) {
        return new OptionalLongex(optionalLong);
    }

    public static OptionalLongex empty() {
        return ofOptional(OptionalLong.empty());
    }

    public static OptionalLongex of(long value) {
        return ofOptional(OptionalLong.of(value));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public long getAsLong() {
        return internal.getAsLong();
    }

    public boolean isPresent() {
        return internal.isPresent();
    }

    public void ifPresent(LongConsumerex consumer) {
        internal.ifPresent(wrap(consumer));
    }

    public long orElse(long other) {
        return internal.orElse(other);
    }

    public long orElseGet(LongSupplierex other) {
        return internal.orElseGet(wrap(other));
    }

    public <X extends Throwable> long orElseThrow(Supplierex<X> exceptionSupplier) throws X {
        return internal.orElseThrow(wrap(exceptionSupplier));
    }
}
