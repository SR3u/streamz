package sr3u.streamz.optionals;

import sr3u.streamz.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleSupplierex;

import java.util.OptionalDouble;
import java.util.function.Supplier;

import static sr3u.streamz.common.primitive.DoubleExceptionWrapper.wrap;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class OptionalDoublex {

    private final OptionalDouble internal;

    private OptionalDoublex(OptionalDouble optionalDouble) {
        this.internal = optionalDouble;
    }

    public static OptionalDoublex ofOptional(OptionalDouble optionalDouble) {
        return new OptionalDoublex(optionalDouble);
    }

    public static OptionalDoublex empty() {
        return ofOptional(OptionalDouble.empty());
    }

    public static OptionalDoublex of(double value) {
        return ofOptional(OptionalDouble.of(value));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public double getAsDouble() {
        return internal.getAsDouble();
    }

    public boolean isPresent() {
        return internal.isPresent();
    }

    public void ifPresent(DoubleConsumerex consumer) {
        internal.ifPresent(wrap(consumer));
    }

    public double orElse(double other) {
        return internal.orElse(other);
    }

    public double orElseGet(DoubleSupplierex other) {
        return internal.orElseGet(wrap(other));
    }

    public <X extends Throwable> double orElseThrow(Supplier<X> exceptionSupplier) throws X {
        return internal.orElseThrow(exceptionSupplier);
    }
}
