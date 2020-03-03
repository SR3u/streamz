package sr3u.streamex.optionals;

import sr3u.streamex.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleSupplierex;

import java.util.OptionalDouble;
import java.util.function.Supplier;

import static sr3u.streamex.common.primitive.DoubleExceptionWrapper.wrap;

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
        return OptionalDoublex.ofOptional(OptionalDouble.empty());
    }

    public static OptionalDoublex of(double value) {
        return OptionalDoublex.ofOptional(OptionalDouble.of(value));
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
