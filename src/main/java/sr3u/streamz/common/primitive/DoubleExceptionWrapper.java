package sr3u.streamz.common.primitive;

import sr3u.streamz.common.ExceptionCreator;
import sr3u.streamz.functionals.primitive.DoubleToIntFunctionex;
import sr3u.streamz.functionals.primitive.DoubleToLongFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleBinaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoublePredicatex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleSupplierex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleUnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.ObjDoubleConsumerex;
import sr3u.streamz.streams.DoubleStreamex;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.stream.DoubleStream;

import static sr3u.streamz.common.ExceptionCreator.createException;

public class DoubleExceptionWrapper {
    public static DoubleBinaryOperator wrap(DoubleBinaryOperatorex op) {
        return (left, right) -> {
            try {
                return op.applyAsDouble(left, right);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> DoubleFunction<R> wrap(DoubleFunctionex<R> f) {
        return i -> {
            try {
                return f.apply(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleConsumer wrap(DoubleConsumerex consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleUnaryOperator wrap(DoubleUnaryOperatorex op) {
        return i -> {
            try {
                return op.applyAsDouble(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoublePredicate wrap(DoublePredicatex predicate) {
        return i -> {
            try {
                return predicate.test(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleSupplier wrap(DoubleSupplierex supplier) {
        return () -> {
            try {
                return supplier.getAsDouble();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleToLongFunction wrap(DoubleToLongFunctionex f) {
        return i -> {
            try {
                return f.applyAsLong(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleToIntFunction wrap(DoubleToIntFunctionex f) {
        return i -> {
            try {
                return f.applyAsInt(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> ObjDoubleConsumer<R> wrap(ObjDoubleConsumerex<R> consumer) {
        return (a, b) -> {
            try {
                consumer.accept(a, b);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static DoubleFunction<? extends DoubleStream> wrapDoubleStream(DoubleFunctionex<? extends DoubleStreamex> mapper) {
        return i -> {
            try {
                return mapper.apply(i).stream();
            } catch (Exception e) {
                throw ExceptionCreator.createException(e);
            }
        };
    }

}
